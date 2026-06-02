package com.shoppers.servicehelpers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

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

    public ServiceResponse postJson(String url, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody == null ? "{}" : jsonBody))
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .timeout(requestTimeout)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new ServiceResponse(response.statusCode(), response.body());
    }

    public ServiceResponse postWithBasicAuth(String url, String username, String password)
            throws IOException, InterruptedException {
        String credentials = Base64.getEncoder().encodeToString(
                (username + ":" + password).getBytes(StandardCharsets.UTF_8));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + credentials)
                .timeout(requestTimeout)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new ServiceResponse(response.statusCode(), response.body());
    }
}
