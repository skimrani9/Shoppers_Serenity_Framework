package com.shoppers.servicehelpers;

import com.google.gson.Gson;
import com.shoppers.constants.Constants;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * REST client for Authentication Service (signup, authenticate, health).
 */
public class AuthenticationApiClient {

    private final ApiClient apiClient;
    private final String baseUrl;
    private final Gson gson = new Gson();

    public AuthenticationApiClient(String baseUrl) {
        this.baseUrl = normalize(baseUrl);
        this.apiClient = new ApiClient();
    }

    public ServiceResponse getHealth() throws IOException, InterruptedException {
        return apiClient.get(baseUrl + Constants.AUTH_TEST_PATH);
    }

    public ServiceResponse signup(String username, String password, String firstName,
                                  String lastName, String email) throws IOException, InterruptedException {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("username", username);
        body.put("password", password);
        body.put("firstname", firstName);
        body.put("lastname", lastName);
        body.put("email", email);
        return apiClient.postJson(baseUrl + Constants.AUTH_SIGNUP_PATH, gson.toJson(body));
    }

    public ServiceResponse authenticate(String username, String password) throws IOException, InterruptedException {
        return apiClient.postWithBasicAuth(baseUrl + Constants.AUTH_AUTHENTICATE_PATH, username, password);
    }

    private static String normalize(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("authentication.service.url is not configured");
        }
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}
