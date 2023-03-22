package com.azure.dapr.Controllers;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Order.Order;

@RestController
public class CreateStateController {
    @PostMapping("/order/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        UUID uuid = UUID.randomUUID();
        System.out.println("Order received: " + uuid);
        System.out.println(order.data);

        order.setKey(uuid);
        order.setData(order.data);
        JSONObject obj = new JSONObject();
        obj.put("key", order.getKey());
        obj.put("data", order.getData());

        System.out.println("Order received: " + obj);

        return new ResponseEntity<String>("Order created with ID: " + uuid, HttpStatus.CREATED);
    }
}
