package com.shoppers.stepdefinitions;

import com.shoppers.helpers.ScenarioContext;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.SearchApiValidator;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * API and multiservice steps for Search & Suggestions module.
 */
public class SearchApiSteps {

    private static final String PREFIX_RESPONSE_KEY = "searchPrefixResponse";
    private static final String DEFAULT_RESPONSE_KEY = "searchDefaultResponse";
    private static final String SUGGESTION_LIST_KEY = "searchSuggestionListResponse";

    @When("the search suggestion service default suggestions API is called")
    public void theSearchSuggestionServiceDefaultSuggestionsApiIsCalled() throws Exception {
        ServiceResponse response = ServiceClientFactory.searchSuggestionClient().getDefaultSearchSuggestions();
        ScenarioContext.put(DEFAULT_RESPONSE_KEY, response);
    }

    @When("the search suggestion service is queried with prefix {string}")
    public void theSearchSuggestionServiceIsQueriedWithPrefix(String prefix) throws Exception {
        ServiceResponse response = ServiceClientFactory.searchSuggestionClient().searchByPrefix(prefix);
        ScenarioContext.put(PREFIX_RESPONSE_KEY, response);
    }

    @When("the common data service search suggestion list API is called")
    public void theCommonDataServiceSearchSuggestionListApiIsCalled() throws Exception {
        ServiceResponse response = ServiceClientFactory.commonDataClient().getSearchSuggestionList();
        ScenarioContext.put(SUGGESTION_LIST_KEY, response);
    }

    @Given("search microservices are healthy and indexed")
    public void searchMicroservicesAreHealthyAndIndexed() throws Exception {
        ServiceResponse list = ServiceClientFactory.commonDataClient().getSearchSuggestionList();
        ServiceResponse defaults = ServiceClientFactory.searchSuggestionClient().getDefaultSearchSuggestions();
        ServiceResponse prefix = ServiceClientFactory.searchSuggestionClient().searchByPrefix("n");
        SearchApiValidator.assertSuccessfulSearchSuggestionList(list);
        SearchApiValidator.assertSuccessfulDefaultSuggestions(defaults);
        SearchApiValidator.assertSuccessfulPrefixSearch(prefix);
        ScenarioContext.put(SUGGESTION_LIST_KEY, list);
        ScenarioContext.put(DEFAULT_RESPONSE_KEY, defaults);
        ScenarioContext.put(PREFIX_RESPONSE_KEY, prefix);
    }

    @Then("the default search suggestions API should return a valid list")
    public void theDefaultSearchSuggestionsApiShouldReturnAValidList() {
        ServiceResponse response = ScenarioContext.get(DEFAULT_RESPONSE_KEY);
        SearchApiValidator.assertSuccessfulDefaultSuggestions(response);
    }

    @Then("the default search suggestions API should return at least {int} items")
    public void theDefaultSearchSuggestionsApiShouldReturnAtLeastItems(int minCount) {
        ServiceResponse response = ScenarioContext.get(DEFAULT_RESPONSE_KEY);
        SearchApiValidator.assertSuccessfulDefaultSuggestions(response);
        if (SearchApiValidator.countDefaultSuggestions(response) < minCount) {
            throw new AssertionError("Expected at least " + minCount + " default suggestions");
        }
    }

    @Then("the prefix search API should return matching suggestions")
    public void thePrefixSearchApiShouldReturnMatchingSuggestions() {
        ServiceResponse response = ScenarioContext.get(PREFIX_RESPONSE_KEY);
        SearchApiValidator.assertSuccessfulPrefixSearch(response);
    }

    @Then("the prefix search API should include keyword containing {string}")
    public void thePrefixSearchApiShouldIncludeKeywordContaining(String keywordPart) {
        ServiceResponse response = ScenarioContext.get(PREFIX_RESPONSE_KEY);
        SearchApiValidator.assertPrefixSearchContainsKeyword(response, keywordPart);
    }

    @Then("the search suggestion list API should return keyword source data")
    public void theSearchSuggestionListApiShouldReturnKeywordSourceData() {
        ServiceResponse response = ScenarioContext.get(SUGGESTION_LIST_KEY);
        SearchApiValidator.assertSuccessfulSearchSuggestionList(response);
    }
}
