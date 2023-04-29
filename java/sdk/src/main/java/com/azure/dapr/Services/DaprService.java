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

    public static Mono<Void> createState(String uuid, State payload) {
        return daprClient().saveState("statestore", uuid, payload);
    }

    public static Mono<io.dapr.client.domain.State<State>> getState(String uuid, Class<State> class1) {
        return daprClient().getState("statestore", uuid, class1);
    }

    public static Mono<Void> deleteState(String uuid) {
        return daprClient().deleteState("statestore", uuid);
    }
}