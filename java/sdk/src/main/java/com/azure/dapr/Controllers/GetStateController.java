package com.azure.dapr.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Entities.State.State;
import com.azure.dapr.Services.DaprService;

import reactor.core.publisher.Mono;

class RetrievedState {
    String key;
    String data;
}

@RestController
public class GetStateController {
    @GetMapping("/order/get/{uuid}")
    public ResponseEntity<String> getState(@PathVariable String uuid) {
        Mono<io.dapr.client.domain.State<State>> response = DaprService.getState(uuid, State.class);
        RetrievedState retrievedState = new RetrievedState();
        retrievedState.data = response.block().getValue().value.toString();
        retrievedState.key = response.block().getValue().key.toString();
        System.out.println("Retrieved class message from state: " + retrievedState);
        return new ResponseEntity<String>("Orders: " + response.block().getValue().value, HttpStatus.OK);
    }
}
