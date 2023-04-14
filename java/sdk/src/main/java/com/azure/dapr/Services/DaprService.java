package com.azure.dapr.Services;

import org.springframework.stereotype.Service;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import reactor.core.publisher.Mono;

@Service
public class DaprService {
    public static DaprClient daprClient() {
        return new DaprClientBuilder().build();
    }

    public static Mono<Void> createState(String uuid, String request) {
        return daprClient().saveState("statestore", uuid, request);
    }
}
