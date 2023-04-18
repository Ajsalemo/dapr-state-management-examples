package com.azure.dapr.Controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Entities.Order.Order;
import com.azure.dapr.Entities.State.State;
import com.azure.dapr.Services.DaprService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CreateStateController {
    @PostMapping("/order/create")
    public ResponseEntity<String> createState(@RequestBody Order order) throws Exception {
        UUID uuid = UUID.randomUUID();

        order.setData(order.data);
        Order newOrder = new Order(order.getData());
        State state = new State(uuid, newOrder);

        State[] payload = new State[] { state };
        ObjectMapper objectMapper = new ObjectMapper();
        // Save state with the DaprService
        DaprService.createState(uuid.toString(), objectMapper.writeValueAsString(payload)).block();
        return new ResponseEntity<String>("Order created with ID: " + uuid, HttpStatus.CREATED);
    }
}
