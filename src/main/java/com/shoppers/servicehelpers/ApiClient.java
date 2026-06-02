package com.shoppers.servicehelpers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Lightweight HTTP client for microservice API integration tests.
 */
public class ApiClient {

    private final HttpClient httpClient;
    private final Duration requestTimeout;

    public ApiClient() {
        this(Duration.ofSeconds(60));
    }

    public ApiClient(Duration requestTimeout) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
        this.requestTimeout = requestTimeout;
    }

    public ServiceResponse get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(requestTimeout)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new ServiceResponse(response.statusCode(), response.body());
    }
}
