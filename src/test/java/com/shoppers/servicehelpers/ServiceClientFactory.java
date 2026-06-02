package com.shoppers.servicehelpers;

import com.shoppers.helpers.TestConfigLoader;

/**
 * Factory for environment-configured microservice API clients.
 */
public final class ServiceClientFactory {

    private ServiceClientFactory() {
    }

    public static CommonDataApiClient commonDataClient() {
        return new CommonDataApiClient(TestConfigLoader.get("common.data.service.url"));
    }

    public static SearchSuggestionApiClient searchSuggestionClient() {
        return new SearchSuggestionApiClient(TestConfigLoader.get("search.suggestion.service.url"));
    }
}
