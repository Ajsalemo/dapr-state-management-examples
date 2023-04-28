package com.azure.dapr.Services;

import org.springframework.stereotype.Service;

import com.azure.dapr.Entities.State.State;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import reactor.core.publisher.Mono;

@Service
public class DaprService {
    public static DaprClient daprClient() {
        return new DaprClientBuilder().build();
    }

    public static Mono<Void> createState(String uuid, State state) {
        return daprClient().saveState("statestore", uuid, state);
    }

    public static Mono<io.dapr.client.domain.State<State>> getState(String uuid, Class<State> state) {
        return daprClient().getState("statestore", uuid, state);
    }
}