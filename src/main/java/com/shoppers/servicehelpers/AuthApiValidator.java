package com.shoppers.servicehelpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Validates Authentication Service API payloads.
 */
public final class AuthApiValidator {

    private AuthApiValidator() {
    }

    public static void assertAuthServiceHealthy(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from auth /test but got "
                    + response.getStatusCode() + " body=" + response.getBody());
        }
        if (!"success".equalsIgnoreCase(response.getBody().trim().replace("\"", ""))) {
            throw new AssertionError("Expected auth health response 'success' but got: " + response.getBody());
        }
    }

    public static void assertSuccessfulSignup(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /signup but got "
                    + response.getStatusCode() + " body=" + response.getBody());
        }
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        if (!json.has("account_creation_status")
                || !"success".equalsIgnoreCase(json.get("account_creation_status").getAsString())) {
            throw new AssertionError("Expected signup success but got: " + response.getBody());
        }
    }

    public static void assertSuccessfulAuthentication(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /authenticate but got "
                    + response.getStatusCode() + " body=" + response.getBody());
        }
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        if (!json.has("jwt") || json.get("jwt").isJsonNull()) {
            throw new AssertionError("Expected jwt in authenticate response but got: " + response.getBody());
        }
    }

    public static void assertFailedAuthentication(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 200 with error for failed auth but got "
                    + response.getStatusCode());
        }
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        if (!json.has("error") || json.get("error").isJsonNull()) {
            throw new AssertionError("Expected error field for failed authentication");
        }
    }
}
