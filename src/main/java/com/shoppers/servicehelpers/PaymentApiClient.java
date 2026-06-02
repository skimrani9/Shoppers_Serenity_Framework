package com.shoppers.servicehelpers;

import com.shoppers.constants.Constants;

import java.io.IOException;

/**
 * REST client for Payment Service health checks.
 */
public class PaymentApiClient {

    private final ApiClient apiClient;
    private final String baseUrl;

    public PaymentApiClient(String baseUrl) {
        this.baseUrl = normalize(baseUrl);
        this.apiClient = new ApiClient();
    }

    public ServiceResponse getHealth() throws IOException, InterruptedException {
        return apiClient.get(baseUrl + Constants.PAYMENT_TEST_PATH);
    }

    private static String normalize(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("payment.service.url is not configured");
        }
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}
