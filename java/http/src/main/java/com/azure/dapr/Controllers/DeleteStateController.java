package com.azure.dapr.Controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Services.HttpService;

@RestController
public class DeleteStateController {
    @Autowired
    private HttpService httpService;

    @DeleteMapping("/order/delete/{uid}")
    public ResponseEntity<String> deleteStateResponse(@PathVariable String uid)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = httpService.deleteState(uid);
        HttpService.restHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new ResponseEntity<String>("Deleted order with Order ID: : " + uid, HttpStatus.OK);
    }
}
