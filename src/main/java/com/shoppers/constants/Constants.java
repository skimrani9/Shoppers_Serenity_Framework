package com.shoppers.constants;

/**
 * Application-wide constants for the Shoppers ecommerce automation framework.
 */
public final class Constants {

    public static final String DEFAULT_ENVIRONMENT = "QA";
    public static final String FEATURES_PATH = "classpath:features";
    public static final String CONFIG_PATH = "config/";
    public static final String TEST_DATA_PATH = "TestData/";

    public static final String HOME_API_PATH = "/home";
    public static final String TABS_API_PATH = "/tabs";
    public static final String DEFAULT_SEARCH_SUGGESTION_PATH = "/default-search-suggestion";
    public static final String SEARCH_SUGGESTION_PATH = "/search-suggestion";
    public static final String SEARCH_SUGGESTION_LIST_PATH = "/search-suggestion-list";
    public static final String PRODUCTS_API_PATH = "/products";
    public static final String FILTER_API_PATH = "/filter";
    public static final String DEFAULT_CATALOG_QUERY = "page=0,16";

    public static final String AUTH_TEST_PATH = "/test";
    public static final String AUTH_SIGNUP_PATH = "/signup";
    public static final String AUTH_AUTHENTICATE_PATH = "/authenticate";

    public static final String PAYMENT_TEST_PATH = "/test";

    private Constants() {
    }
}
