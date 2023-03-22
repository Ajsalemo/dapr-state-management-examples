package com.azure.dapr.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    String message = "dapr-state-management-examples-java";

    @GetMapping("/")
    public String index() {
        return message;
    }
}
