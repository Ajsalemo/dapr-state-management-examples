package com.azure.dapr.Controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Entities.Order.Order;
import com.azure.dapr.Entities.State.State;
import com.azure.dapr.Services.HttpService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CreateStateController {
    @Autowired
    private HttpService httpService;

    @PostMapping("/order/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order)
            throws IOException, InterruptedException, URISyntaxException {
        UUID uuid = UUID.randomUUID();

        order.setData(order.data);
        Order newOrder = new Order(order.getData());
        State state = new State(uuid, newOrder);

        State[] payload = new State[] { state };
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Order received: " + objectMapper.writeValueAsString(payload));

        HttpRequest request = httpService
                .createState(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(payload)));
        HttpService.restHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new ResponseEntity<String>("Order created with ID: " + uuid, HttpStatus.CREATED);
    }
}