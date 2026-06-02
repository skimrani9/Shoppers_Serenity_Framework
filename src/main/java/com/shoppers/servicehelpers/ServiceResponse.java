package com.shoppers.servicehelpers;

/**
 * Generic API response wrapper for REST test helpers.
 */
public class ServiceResponse {

    private final int statusCode;
    private final String body;

    public ServiceResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 300;
    }
}
