package com.shoppers.servicehelpers;

/**
 * Validates Payment Service health responses.
 */
public final class PaymentApiValidator {

    private PaymentApiValidator() {
    }

    public static void assertPaymentServiceHealthy(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from payment /test but got "
                    + response.getStatusCode() + " body=" + response.getBody());
        }
        if (!"success".equalsIgnoreCase(response.getBody().trim().replace("\"", ""))) {
            throw new AssertionError("Expected payment health response 'success' but got: " + response.getBody());
        }
    }
}
