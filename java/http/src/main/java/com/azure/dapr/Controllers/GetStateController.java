package com.azure.dapr.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetStateController {
    @GetMapping("/order/get/{uid}")
    public String getStateResponse(@PathVariable String uid) {
        System.out.println("T");

        return uid;
    }
}
