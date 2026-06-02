package com.shoppers.stepdefinitions;

import com.shoppers.constants.Constants;
import com.shoppers.helpers.ScenarioContext;
import com.shoppers.helpers.TestConfigLoader;
import com.shoppers.servicehelpers.CatalogApiValidator;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Product Catalog & Filtering — API steps (Module 4).
 */
public class CatalogApiSteps {

    private static final String CATALOG_KEY = "catalogApiResponse";
    private static final String FILTER_KEY = "filterApiResponse";

    @When("the common data service catalog API is called with default pagination")
    public void theCommonDataServiceCatalogApiIsCalledWithDefaultPagination() throws Exception {
        String query = TestConfigLoader.get("products.default.query", Constants.DEFAULT_CATALOG_QUERY);
        ServiceResponse response = ServiceClientFactory.commonDataClient().getProductsByQuery(query);
        ScenarioContext.put(CATALOG_KEY, response);
    }

    @When("the common data service filter API is called with default pagination")
    public void theCommonDataServiceFilterApiIsCalledWithDefaultPagination() throws Exception {
        String query = TestConfigLoader.get("products.default.query", Constants.DEFAULT_CATALOG_QUERY);
        ServiceResponse response = ServiceClientFactory.commonDataClient().getFilterByQuery(query);
        ScenarioContext.put(FILTER_KEY, response);
    }

    @Given("catalog and filter APIs are healthy")
    public void catalogAndFilterApisAreHealthy() throws Exception {
        String query = TestConfigLoader.get("products.default.query", Constants.DEFAULT_CATALOG_QUERY);
        ServiceResponse catalog = ServiceClientFactory.commonDataClient().getProductsByQuery(query);
        ServiceResponse filter = ServiceClientFactory.commonDataClient().getFilterByQuery(query);
        CatalogApiValidator.assertSuccessfulCatalogResponse(catalog);
        CatalogApiValidator.assertSuccessfulFilterResponse(filter);
        ScenarioContext.put(CATALOG_KEY, catalog);
        ScenarioContext.put(FILTER_KEY, filter);
    }

    @Then("the catalog API should return a valid product listing")
    public void theCatalogApiShouldReturnAValidProductListing() {
        CatalogApiValidator.assertSuccessfulCatalogResponse(ScenarioContext.get(CATALOG_KEY));
    }

    @Then("the filter API should return filter attributes")
    public void theFilterApiShouldReturnFilterAttributes() {
        CatalogApiValidator.assertSuccessfulFilterResponse(ScenarioContext.get(FILTER_KEY));
    }
}
