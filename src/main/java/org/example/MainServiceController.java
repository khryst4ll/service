package org.example;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.RequestDto;
import org.example.model.ResponseDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainServiceController {
    private final UserService userService;

    @PostMapping("/address")
    public ResponseDto createUserWithAddress(@RequestHeader("USER_ID") String userId,
                                             @Valid @RequestBody RequestDto request) {
        return this.userService.createUserWithAddress(userId, request);
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi";
    }
}
