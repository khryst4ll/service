package org.example.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.example.model.RequestDto;
import org.example.model.ResponseDto;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final DadataService dadataService;

    public ResponseDto createUserWithAddress(@RequestHeader("USER_ID") String userId, @RequestBody RequestDto request) {
//        this.kafkaMsgSender.sendMsg(request);
        String pureAddress = this.dadataService.purifyAddress(request.getAddress());
        User user;
        ResponseDto responseDto;
        if (this.getAddressByUserId(userId) != null) {
            user = this.getUserById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Пользователь с таким айди не найден в БД");
            }
            this.updateUser(user, userId, request.getAddress(), pureAddress, true);
            responseDto = new ResponseDto(userId, pureAddress, true);
        }
        else {
            this.createUser(userId, request.getAddress(), pureAddress);
            responseDto = new ResponseDto(userId, pureAddress, false);
        }

        return responseDto;
    }

    public User createUser(String userStringId, String address, String ddtAddress) {
        User user = new User();
        user.setUserStringId(userStringId);
        user.setUserAddress(address);
        user.setDdtAddress(ddtAddress);
        user.setUserStatus(false);

        return this.repository.save(user);
    }

    public void updateUser(User user, String userStringId, String address, String ddtAddress, boolean userStatus) {
        user.setUserStringId(userStringId);
        user.setUserAddress(address);
        user.setDdtAddress(ddtAddress);
        user.setUserStatus(userStatus);
        this.repository.save(user);
    }

    public @Nullable String getAddressByUserId(String userId) {
        Optional<String> address = this.repository.findAddressByUserId(userId);

        return address.orElse(null);
    }

    public @Nullable User getUserById(String userId) {
        Optional<User> address = this.repository.findByUserStringId(userId);

        return address.orElse(null);
    }
}
