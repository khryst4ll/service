package org.example;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyServiceController {
    @Autowired
    private KafkaMsgSender kafkaMsgSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DadataController dadataController;

    @PostMapping("/address")
    public ResponseEntity<String> createAddress(@RequestHeader("USER_ID") String userId, @RequestBody Request request) {

        String response = this.dadataController.purifyAddress(request.getAddress());
        this.kafkaMsgSender.sendMsg("Получен юзер " + userId + " с адресом " + response);
        User user;
        if (this.getAddressByUserId(userId) != null) {
            user = this.getUserById(userId);
            user.setUserAddress(request.getAddress());
            user.setDdtAddress(response);
            user.setUserStatus(true);
        }
        else {
            user = new User(userId, request.getAddress(), response, false);
        }

        this.userRepository.save(user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi";
    }

    @GetMapping("/user/{id}/address")
    public @Nullable String getAddressByUserId(String userId) {
        Optional<String> address = this.userRepository.findAddressByUserId(userId);

        return address.orElse(null);
    }

    public @Nullable User getUserById(String userId) {
        Optional<User> address = this.userRepository.findByUserStringId(userId);

        return address.orElse(null);
    }
}
