package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // ← Обязательно!
@AllArgsConstructor // ← Опционально
public class Request {
    private String address;
}