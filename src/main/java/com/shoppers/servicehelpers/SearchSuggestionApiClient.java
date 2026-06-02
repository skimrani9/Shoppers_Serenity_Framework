package com.shoppers.servicehelpers;

import com.shoppers.constants.Constants;

import java.io.IOException;

/**
 * REST client for Search Suggestion Service (used by home page search bar).
 */
public class SearchSuggestionApiClient {

    private final ApiClient apiClient;
    private final String baseUrl;

    public SearchSuggestionApiClient(String baseUrl) {
        this.baseUrl = normalize(baseUrl);
        this.apiClient = new ApiClient();
    }

    public ServiceResponse getDefaultSearchSuggestions() throws IOException, InterruptedException {
        return apiClient.get(baseUrl + Constants.DEFAULT_SEARCH_SUGGESTION_PATH);
    }

    public ServiceResponse searchByPrefix(String prefix) throws IOException, InterruptedException {
        return apiClient.get(baseUrl + Constants.SEARCH_SUGGESTION_PATH + "?q=" + prefix);
    }

    private static String normalize(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("search.suggestion.service.url is not configured");
        }
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}
