package com.azure.dapr.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Order.Order;
import com.azure.dapr.Services.RestService;

@RestController
public class CreateStateController {
    @Autowired
    private RestService restService;

    @PostMapping("/order/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) throws IOException, InterruptedException {
        UUID uuid = UUID.randomUUID();

        order.setKey(uuid);
        order.setData(order.data);
        JSONObject obj = new JSONObject();
        obj.put("key", order.getKey());
        obj.put("data", order.getData());

        List<JSONObject> orderList = new ArrayList<JSONObject>();
        orderList.add(obj);

        System.out.println("Order received: " + orderList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<List<JSONObject>> entity = new HttpEntity<List<JSONObject>>(orderList, headers);
        ResponseEntity<CreateStateController> response = restService.createState(entity, CreateStateController.class);
        System.out.println(response.getBody());

        return new ResponseEntity<String>("Order created with ID: " + uuid, HttpStatus.CREATED);
    }
}