package com.shoppers.stepdefinitions;

import com.shoppers.helpers.ScenarioContext;
import com.shoppers.pageObjects.NavigationPage;
import com.shoppers.pageObjects.ProductCatalogPage;
import com.shoppers.servicehelpers.HomeApiValidator;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Navigation & Category Tabs — API and UI steps (Module 2).
 */
public class NavigationApiSteps {

    private static final String TABS_RESPONSE_KEY = "tabsApiResponse";

    @Given("navigation tabs API is healthy")
    public void navigationTabsApiIsHealthy() throws Exception {
        ServiceResponse response = ServiceClientFactory.commonDataClient().getTabsData();
        HomeApiValidator.assertSuccessfulTabsResponse(response);
        ScenarioContext.put(TABS_RESPONSE_KEY, response);
    }

    @When("the common data service tabs API is called for navigation")
    public void theCommonDataServiceTabsApiIsCalledForNavigation() throws Exception {
        ServiceResponse response = ServiceClientFactory.commonDataClient().getTabsData();
        ScenarioContext.put(TABS_RESPONSE_KEY, response);
    }

    @Then("the navigation tabs API should return gender navigation data")
    public void theNavigationTabsApiShouldReturnGenderNavigationData() {
        ServiceResponse response = ScenarioContext.get(TABS_RESPONSE_KEY);
        HomeApiValidator.assertSuccessfulTabsResponse(response);
    }
}
