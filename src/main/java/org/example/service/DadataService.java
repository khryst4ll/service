package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.properties.DadataProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DadataService {
    private final DadataProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String purifyAddress(String address) {
        String pureAddress = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + this.properties.getToken());
        headers.set("X-Secret", this.properties.getSecret());

        String[] requestBody = {address};
        HttpEntity<String[]> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    this.properties.getUrl(),
                    entity,
                    String.class);
            JsonNode[] nodes = this.objectMapper.readValue(response.getBody(), JsonNode[].class);
            if (nodes.length > 0) {
                JsonNode firstResult = nodes[0];
                pureAddress = firstResult.get("result").asText();
                return pureAddress;
            }
            return response.toString();
        }
        catch (Exception e) {
            System.err.println("Ошибка при вызове DaData: " + e.getMessage());
            return "";
        }
    }
}
