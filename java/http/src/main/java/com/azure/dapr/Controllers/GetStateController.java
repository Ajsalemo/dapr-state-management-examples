package com.azure.dapr.Controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Services.HttpService;

@RestController
public class GetStateController {
    @Autowired
    private HttpService httpService;

    @GetMapping("/order/get/{uid}")
    public ResponseEntity<String> getStateResponse(@PathVariable String uid)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = httpService.getState(uid);
        HttpResponse<String> response = HttpService.restHttpClient().send(request,
                HttpResponse.BodyHandlers.ofString());

        return new ResponseEntity<String>("Orders: " + response.body(), HttpStatus.OK);
    }
}
