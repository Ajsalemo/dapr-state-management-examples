package com.azure.dapr.Services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.azure.dapr.Controllers.CreateStateController;

@Service
public class RestService {
    private final RestTemplate restTemplate;
    String url = "http://localhost:3500/v1.0/state/statestore";

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<CreateStateController> createState(HttpEntity<String> entity, Class<CreateStateController> className) {
        return this.restTemplate.postForEntity(url, entity, className);
    }
}