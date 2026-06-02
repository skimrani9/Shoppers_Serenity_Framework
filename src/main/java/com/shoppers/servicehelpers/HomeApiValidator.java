package com.shoppers.servicehelpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Validates Common Data Service /home and related home-discovery API payloads.
 */
public final class HomeApiValidator {

    private HomeApiValidator() {
    }

    public static void assertSuccessfulHomeResponse(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /home but got " + response.getStatusCode()
                    + " body=" + truncate(response.getBody()));
        }

        JsonObject json = parseObject(response.getBody());
        assertNonEmptyArray(json, "brands");
        assertNonEmptyArray(json, "apparels");
        assertNonEmptyArray(json, "carousels");
    }

    public static void assertSuccessfulTabsResponse(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /tabs but got " + response.getStatusCode()
                    + " body=" + truncate(response.getBody()));
        }

        JsonObject json = parseObject(response.getBody());
        String[] genderKeys = {"men", "women", "boys", "girls", "essentials", "homeAndLiving"};
        for (String key : genderKeys) {
            if (!json.has(key)) {
                throw new AssertionError("Tabs response missing key: " + key);
            }
        }
    }

    public static void assertSuccessfulDefaultSearchSuggestions(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /default-search-suggestion but got "
                    + response.getStatusCode() + " body=" + truncate(response.getBody()));
        }

        JsonArray array = JsonParser.parseString(response.getBody()).getAsJsonArray();
        if (array.isEmpty()) {
            throw new AssertionError("Expected non-empty default search suggestions list");
        }

        JsonObject first = array.get(0).getAsJsonObject();
        if (!first.has("keyword")) {
            throw new AssertionError("Search suggestion item missing 'keyword' field");
        }
    }

    public static int countArrayItems(ServiceResponse homeResponse, String field) {
        JsonObject json = parseObject(homeResponse.getBody());
        JsonArray array = json.getAsJsonArray(field);
        return array == null ? 0 : array.size();
    }

    private static void assertNonEmptyArray(JsonObject json, String field) {
        JsonElement element = json.get(field);
        if (element == null || !element.isJsonArray() || element.getAsJsonArray().isEmpty()) {
            throw new AssertionError("Expected non-empty array '" + field + "' in /home response");
        }
    }

    private static JsonObject parseObject(String body) {
        return JsonParser.parseString(body).getAsJsonObject();
    }

    private static String truncate(String body) {
        if (body == null) {
            return "";
        }
        return body.length() > 300 ? body.substring(0, 300) + "..." : body;
    }
}
