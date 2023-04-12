package com.azure.dapr.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Entities.Order.Order;
import com.azure.dapr.Entities.State.State;
import com.azure.dapr.Services.DaprService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CreateStateController {
    @PostMapping("/order/create")
    public String createState(@RequestBody Order order) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();

        order.setData(order.data);
        Order newOrder = new Order(order.getData());
        State state = new State(uuid, newOrder);

        State[] payload = new State[] { state };
        ObjectMapper objectMapper = new ObjectMapper();

        DaprService.createState(uuid.toString(), objectMapper.writeValueAsString(payload));

        return "T";
    }
}
