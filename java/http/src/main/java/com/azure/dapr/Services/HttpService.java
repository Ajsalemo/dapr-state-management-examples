package com.azure.dapr.Services;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;

import org.springframework.stereotype.Service;

@Service
public class HttpService { 
    public URI constructDaprUri() throws URISyntaxException {
        return new URI("http://localhost:3500/v1.0/state/statestore");
    }

    public HttpRequest createState(BodyPublisher string) throws URISyntaxException {
        return HttpRequest.newBuilder().POST(string).uri(constructDaprUri()).build();
    }

}