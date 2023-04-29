package com.azure.dapr.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.azure.dapr.Services.DaprService;

@RestController
public class DeleteStateController {
    @DeleteMapping("/order/delete/{uuid}")
    public ResponseEntity<String> deleteMapping(@PathVariable String uuid) {
        DaprService.deleteState(uuid).block();
        return new ResponseEntity<String>("Deleted order with Order ID: : " + uuid, HttpStatus.OK);
    }
}
