package com.shoppers.stepdefinitions;

import com.shoppers.helpers.ScenarioContext;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.HomeApiValidator;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Home API and multiservice integration scenarios.
 */
public class HomeApiSteps {

    private static final String HOME_RESPONSE_KEY = "homeApiResponse";
    private static final String TABS_RESPONSE_KEY = "tabsApiResponse";
    private static final String SEARCH_RESPONSE_KEY = "searchApiResponse";

    @When("the common data service home API is called")
    public void theCommonDataServiceHomeApiIsCalled() throws Exception {
        ServiceResponse response = ServiceClientFactory.commonDataClient().getHomeScreenData();
        ScenarioContext.put(HOME_RESPONSE_KEY, response);
    }

    @When("the common data service tabs API is called")
    public void theCommonDataServiceTabsApiIsCalled() throws Exception {
        ServiceResponse response = ServiceClientFactory.commonDataClient().getTabsData();
        ScenarioContext.put(TABS_RESPONSE_KEY, response);
    }

    @Then("the home API should return valid main screen data")
    public void theHomeApiShouldReturnValidMainScreenData() {
        ServiceResponse response = ScenarioContext.get(HOME_RESPONSE_KEY);
        HomeApiValidator.assertSuccessfulHomeResponse(response);
    }

    @Then("the home API brands list should not be empty")
    public void theHomeApiBrandsListShouldNotBeEmpty() {
        ServiceResponse response = ScenarioContext.get(HOME_RESPONSE_KEY);
        int count = HomeApiValidator.countArrayItems(response, "brands");
        if (count <= 0) {
            throw new AssertionError("Expected at least one brand in /home response");
        }
    }

    @Then("the home API carousels list should not be empty")
    public void theHomeApiCarouselsListShouldNotBeEmpty() {
        ServiceResponse response = ScenarioContext.get(HOME_RESPONSE_KEY);
        int count = HomeApiValidator.countArrayItems(response, "carousels");
        if (count <= 0) {
            throw new AssertionError("Expected at least one carousel in /home response");
        }
    }

    @Then("the tabs API should return gender navigation data")
    public void theTabsApiShouldReturnGenderNavigationData() {
        ServiceResponse response = ScenarioContext.get(TABS_RESPONSE_KEY);
        HomeApiValidator.assertSuccessfulTabsResponse(response);
    }

    @Given("all backend services required for home discovery are healthy")
    public void allBackendServicesRequiredForHomeDiscoveryAreHealthy() throws Exception {
        ServiceResponse home = ServiceClientFactory.commonDataClient().getHomeScreenData();
        ServiceResponse search = ServiceClientFactory.searchSuggestionClient().getDefaultSearchSuggestions();
        HomeApiValidator.assertSuccessfulHomeResponse(home);
        HomeApiValidator.assertSuccessfulDefaultSearchSuggestions(search);
        ScenarioContext.put(HOME_RESPONSE_KEY, home);
        ScenarioContext.put(SEARCH_RESPONSE_KEY, search);
    }
}
