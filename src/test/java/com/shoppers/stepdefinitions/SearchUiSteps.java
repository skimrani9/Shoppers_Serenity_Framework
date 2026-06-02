package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.ProductCatalogPage;
import com.shoppers.pageObjects.SearchBarPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * E2E / functional UI steps for Search & Suggestions module.
 */
public class SearchUiSteps {

    @Steps
    SearchBarPage searchBarPage;

    @Steps
    ProductCatalogPage productCatalogPage;

    @Given("the shopper is on the homepage with search available")
    public void theShopperIsOnTheHomepageWithSearchAvailable() {
        searchBarPage.openHomePageForSearch();
    }

    @When("the shopper focuses the search bar")
    public void theShopperFocusesTheSearchBar() {
        searchBarPage.focusDesktopSearchBar();
    }

    @Given("the shopper is on the homepage at mobile viewport")
    public void theShopperIsOnTheHomepageAtMobileViewport() {
        searchBarPage.openHomePageAtMobileViewport();
    }

    @When("the shopper opens mobile search from the navbar")
    public void theShopperOpensMobileSearchFromTheNavbar() {
        searchBarPage.openMobileSearch();
    }

    @When("the shopper types {string} in the search bar")
    public void theShopperTypesInTheSearchBar(String prefix) {
        searchBarPage.typeInSearchBar(prefix);
    }

    @When("the shopper selects the search suggestion {string}")
    public void theShopperSelectsTheSearchSuggestion(String keyword) {
        searchBarPage.selectSearchSuggestion(keyword);
    }

    @When("the shopper selects the first search suggestion")
    public void theShopperSelectsTheFirstSearchSuggestion() {
        searchBarPage.selectFirstSearchSuggestion();
    }

    @Then("default search suggestions should appear")
    public void defaultSearchSuggestionsShouldAppear() {
        searchBarPage.verifyDefaultSearchSuggestionsDisplayed();
    }

    @Then("typeahead search suggestions should appear")
    public void typeaheadSearchSuggestionsShouldAppear() {
        searchBarPage.verifyTypeaheadSuggestionsDisplayed();
    }

    @Then("typeahead suggestions should include {string}")
    public void typeaheadSuggestionsShouldInclude(String keywordPart) {
        searchBarPage.verifyTypeaheadIncludesKeyword(keywordPart);
    }

    @Then("the shopper should land on the product catalog from search")
    public void theShopperShouldLandOnTheProductCatalogFromSearch() {
        productCatalogPage.verifyProductCatalogPageDisplayed();
    }

    @Then("the product catalog URL should reflect the search filter")
    public void theProductCatalogUrlShouldReflectTheSearchFilter() {
        productCatalogPage.verifyProductCatalogPageDisplayed();
        productCatalogPage.verifyUrlContainsSearchFilter();
    }
}
