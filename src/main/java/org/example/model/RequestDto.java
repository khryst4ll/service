package org.example.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    @NotBlank(message = "Адрес не должен быть пустым")
    private String address;
    @NotBlank(message = "UserStrId не должен быть пустым")
    private String userStrId;
}