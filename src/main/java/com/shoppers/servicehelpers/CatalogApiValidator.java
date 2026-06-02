package com.shoppers.servicehelpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Validates Common Data Service catalog and filter API payloads.
 */
public final class CatalogApiValidator {

    private CatalogApiValidator() {
    }

    public static void assertSuccessfulFilterResponse(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /filter?q= but got "
                    + response.getStatusCode() + " body=" + truncate(response.getBody()));
        }
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        requireArray(json, "genders");
        requireArray(json, "apparels");
        requireArray(json, "brands");
        requireArray(json, "prices");
        requireArray(json, "sortby");
    }

    public static void assertSuccessfulCatalogResponse(ServiceResponse response) {
        ProductDetailsApiValidator.assertSuccessfulProductListResponse(response);
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        if (!json.has("totalCount")) {
            throw new AssertionError("Expected totalCount in catalog response");
        }
    }

    public static String extractFirstGenderId(ServiceResponse filterResponse) {
        JsonObject json = JsonParser.parseString(filterResponse.getBody()).getAsJsonObject();
        JsonArray genders = json.getAsJsonArray("genders");
        if (genders.isEmpty()) {
            throw new AssertionError("Expected at least one gender in filter response");
        }
        return String.valueOf(genders.get(0).getAsJsonObject().get("id").getAsInt());
    }

    private static void requireArray(JsonObject json, String field) {
        if (!json.has(field) || !json.get(field).isJsonArray()) {
            throw new AssertionError("Expected array '" + field + "' in filter response");
        }
    }

    private static String truncate(String body) {
        if (body == null) {
            return "";
        }
        return body.length() > 300 ? body.substring(0, 300) + "..." : body;
    }
}
