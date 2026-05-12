package org.example;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.RequestDto;
import org.example.model.ResponseDto;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainServiceController {
    private final UserService userService;

    @PostMapping("/address")
    public ResponseEntity<Map<String, String>> createUserWithAddress(@Valid @RequestBody RequestDto request) {
         this.userService.createUserWithAddress(request);
        return ResponseEntity
                .accepted()
                .body(Map.of("userId", request.getUserStrId(), "status", "processing"));
    }

    @GetMapping("/user")
    public ResponseDto getResult(@Valid @RequestBody RequestDto request) {
        User user = this.userService.getUserByStrId(request.getUserStrId());
        return new ResponseDto(user.getUserStringId(), user.getAddress().getPureAddress(), user.getUserStatus());
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi";
    }
}
