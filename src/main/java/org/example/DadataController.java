package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DadataController {

    private final DadataProperites properties;
    private final RestTemplate restTemplate;

    @Autowired
    public DadataController(DadataProperites properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    public String purifyAddress(String address) {
        String pureAddress = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + this.properties.getApiToken());
        headers.set("X-Secret", this.properties.getSecret());

        String[] requestBody = {address};
        HttpEntity<String[]> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    this.properties.getUrl(),
                    entity,
                    String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode[] nodes = mapper.readValue(response.getBody(), JsonNode[].class);
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
