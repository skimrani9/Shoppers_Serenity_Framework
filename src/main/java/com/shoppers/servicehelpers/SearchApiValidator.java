package com.shoppers.servicehelpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Validates Search Suggestion Service and related common-data search APIs.
 */
public final class SearchApiValidator {

    private SearchApiValidator() {
    }

    public static void assertSuccessfulDefaultSuggestions(ServiceResponse response) {
        HomeApiValidator.assertSuccessfulDefaultSearchSuggestions(response);
    }

    public static void assertSuccessfulPrefixSearch(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /search-suggestion but got "
                    + response.getStatusCode() + " body=" + truncate(response.getBody()));
        }
        if (response.getBody() == null || response.getBody().isBlank() || "null".equals(response.getBody())) {
            throw new AssertionError("Expected non-empty prefix search response");
        }
        JsonArray array = JsonParser.parseString(response.getBody()).getAsJsonArray();
        if (array.isEmpty()) {
            throw new AssertionError("Expected at least one search suggestion for prefix query");
        }
        assertSuggestionItemStructure(array.get(0).getAsJsonObject());
    }

    public static void assertPrefixSearchContainsKeyword(ServiceResponse response, String keywordPart) {
        assertSuccessfulPrefixSearch(response);
        JsonArray array = JsonParser.parseString(response.getBody()).getAsJsonArray();
        boolean found = false;
        for (JsonElement element : array) {
            String keyword = element.getAsJsonObject().get("keyword").getAsString();
            if (keyword.toLowerCase().contains(keywordPart.toLowerCase())) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new AssertionError("Expected suggestion containing '" + keywordPart + "' in prefix search response");
        }
    }

    public static void assertSuccessfulSearchSuggestionList(ServiceResponse response) {
        if (!response.isSuccessful()) {
            throw new AssertionError("Expected HTTP 2xx from /search-suggestion-list but got "
                    + response.getStatusCode() + " body=" + truncate(response.getBody()));
        }
        JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
        String[] keys = {"genderKeywords", "brandKeywords", "apparelKeywords", "productKeywords"};
        for (String key : keys) {
            if (!json.has(key) || !json.get(key).isJsonArray() || json.getAsJsonArray(key).isEmpty()) {
                throw new AssertionError("Expected non-empty array '" + key + "' in /search-suggestion-list");
            }
        }
    }

    public static int countDefaultSuggestions(ServiceResponse response) {
        JsonArray array = JsonParser.parseString(response.getBody()).getAsJsonArray();
        return array.size();
    }

    private static void assertSuggestionItemStructure(JsonObject item) {
        if (!item.has("keyword")) {
            throw new AssertionError("Search suggestion item missing 'keyword'");
        }
        if (!item.has("link")) {
            throw new AssertionError("Search suggestion item missing 'link'");
        }
    }

    private static String truncate(String body) {
        if (body == null) {
            return "";
        }
        return body.length() > 300 ? body.substring(0, 300) + "..." : body;
    }
}
