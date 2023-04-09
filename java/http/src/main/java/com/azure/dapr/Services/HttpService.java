package com.azure.dapr.Services;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.time.Duration;

import org.springframework.stereotype.Service;

@Service
public class HttpService {
    public URI constructDaprCreateStateUri() throws URISyntaxException {
        return new URI("http://localhost:3500/v1.0/state/statestore");
    }

    public URI constructDaprGetOrDeleteStateUri(String uid) throws URISyntaxException {
        return new URI("http://localhost:3500/v1.0/state/statestore" + "/" + uid);
    }

    public HttpRequest createState(BodyPublisher string) throws URISyntaxException {
        return HttpRequest.newBuilder().POST(string).uri(constructDaprCreateStateUri()).build();
    }

    public HttpRequest getState(String uid) throws URISyntaxException {
        return HttpRequest.newBuilder().GET().uri(constructDaprGetOrDeleteStateUri(uid)).build();
    }

    public HttpRequest deleteState(String uid) throws URISyntaxException {
        return HttpRequest.newBuilder().DELETE().uri(constructDaprGetOrDeleteStateUri(uid)).build();
    }

    public static final HttpClient restHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

}