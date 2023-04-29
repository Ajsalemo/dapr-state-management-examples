package com.azure.dapr.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Entities.State.State;
import com.azure.dapr.Services.DaprService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
class RetrievedState {
    public String key;
    public String value;
}

@RestController
public class GetStateController {
    @GetMapping("/order/get/{uuid}")
    public ResponseEntity<String> getState(@PathVariable String uuid) throws JsonProcessingException {
        Mono<io.dapr.client.domain.State<State>> response = DaprService.getState(uuid, State.class);
        RetrievedState retrievedState = new RetrievedState();
        retrievedState.setValue(response.block().getValue().value.toString()); 
        retrievedState.setKey(response.block().getKey().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(retrievedState);
        return new ResponseEntity<String>("Orders: " + json.toString(), HttpStatus.OK);
    }
}
