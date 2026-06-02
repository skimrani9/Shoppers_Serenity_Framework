package com.shoppers.servicehelpers;

import com.shoppers.constants.Constants;

import java.io.IOException;

/**
 * REST client for Common Data Service (home, tabs, products).
 */
public class CommonDataApiClient {

    private final ApiClient apiClient;
    private final String baseUrl;

    public CommonDataApiClient(String baseUrl) {
        this.baseUrl = normalize(baseUrl);
        this.apiClient = new ApiClient();
    }

    public ServiceResponse getHomeScreenData() throws IOException, InterruptedException {
        return apiClient.get(baseUrl + Constants.HOME_API_PATH);
    }

    public ServiceResponse getTabsData() throws IOException, InterruptedException {
        return apiClient.get(baseUrl + Constants.TABS_API_PATH);
    }

    public ServiceResponse getSearchSuggestionList() throws IOException, InterruptedException {
        return apiClient.get(baseUrl + Constants.SEARCH_SUGGESTION_LIST_PATH);
    }

    private static String normalize(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("common.data.service.url is not configured");
        }
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}
