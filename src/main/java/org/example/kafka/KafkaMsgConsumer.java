package org.example.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.model.Address;
import org.example.model.RequestDto;
import org.example.model.ResponseDto;
import org.example.model.User;
import org.example.service.AddressService;
import org.example.service.DadataService;
import org.example.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMsgConsumer {

    private final ObjectMapper objectMapper;
    private final DadataService dadataService;
    private final UserService userService;
    private final AddressService addressService;

    @KafkaListener(topics = "default-topic", groupId = "main-group")
    public ResponseDto listen(String message) {
        System.out.println("Получено сообщение: " + message);

        try {
            RequestDto requestDto = this.objectMapper.readValue(message, RequestDto.class);
            return this.processDto(requestDto);
        }
        catch (Exception e) {
            System.out.println("Ошибка десериализации: " + e.getMessage());
        }

        return null;
    }

    private ResponseDto processDto(RequestDto requestDto) {
        String pureAddress = this.dadataService.purifyAddress(requestDto.getAddress());
        User user = this.userService.getUserByStrId(requestDto.getUserStrId());
        if (user == null) {
            this.userService.createUser(requestDto.getUserStrId(), pureAddress);
        }
        user = this.userService.updateUser(requestDto.getUserStrId(), pureAddress);

        return new ResponseDto(user.getUserStringId(), user.getAddress().getPureAddress(), user.getUserStatus());
    }
}