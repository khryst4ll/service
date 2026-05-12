package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.example.kafka.KafkaMsgConsumer;
import org.example.kafka.KafkaMsgSender;
import org.example.model.Address;
import org.example.model.RequestDto;
import org.example.model.ResponseDto;
import org.example.model.User;
import org.example.repository.AddressRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final KafkaMsgSender kafkaMsgSender;
    private final ObjectMapper objectMapper;

    public void createUserWithAddress(@RequestBody RequestDto request) {
        try {
            String json = this.objectMapper.writeValueAsString(request);
            this.kafkaMsgSender.sendMsg(json);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public User createUser(String userStrId, String pureAddress) {
        Address address = this.addressService.getOrCreate(pureAddress);
        User user = new User();
        user.setUserStringId(userStrId);
        user.setUserStatus(true);
        user.setAddress(address);

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(String userStrId, String pureAddress) {
        User user = this.userRepository.findByUserStringId(userStrId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + userStrId));
        Address address = this.addressService.getOrCreate(pureAddress);
        user.setAddress(address);

        return userRepository.save(user);
    }

    public @Nullable User getUserByStrId(String userId) {
        Optional<User> user = this.userRepository.findByUserStringId(userId);

        return user.orElse(null);
    }
}
