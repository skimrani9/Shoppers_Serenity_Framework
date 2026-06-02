package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.HomeDiscoveryPage;
import com.shoppers.pageObjects.ProductCatalogPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Step definitions for Home & Discovery UI scenarios (@home @e2e @functional).
 */
public class HomeDiscoverySteps {

    @Steps
    HomeDiscoveryPage homeDiscoveryPage;

    @Steps
    ProductCatalogPage productCatalogPage;

    @Given("the shopper opens the Shoppers home page")
    public void theShopperOpensTheShoppersHomePage() {
        homeDiscoveryPage.openHomePage();
        homeDiscoveryPage.waitForHomePageLoaded();
    }

    @Given("the home page has fully loaded")
    public void theHomePageHasFullyLoaded() {
        homeDiscoveryPage.waitForHomePageLoaded();
    }

    @Then("the home page should display the discovery layout")
    public void theHomePageShouldDisplayTheDiscoveryLayout() {
        homeDiscoveryPage.verifyHomePageTitle();
        homeDiscoveryPage.verifyHeroCarouselDisplayed();
        homeDiscoveryPage.verifyTopBrandsSectionDisplayed();
        homeDiscoveryPage.verifyTopCategoriesSectionDisplayed();
    }

    @Then("the Shop Top Brands section should show brand tiles")
    public void theShopTopBrandsSectionShouldShowBrandTiles() {
        homeDiscoveryPage.verifyTopBrandsSectionDisplayed();
    }

    @Then("the Shop Top Categories section should show category tiles")
    public void theShopTopCategoriesSectionShouldShowCategoryTiles() {
        homeDiscoveryPage.verifyTopCategoriesSectionDisplayed();
    }

    @Then("the hero carousel should be visible on the home page")
    public void theHeroCarouselShouldBeVisibleOnTheHomePage() {
        homeDiscoveryPage.verifyHeroCarouselDisplayed();
    }

    @Then("home menu quick-link icons should be visible")
    public void homeMenuQuickLinkIconsShouldBeVisible() {
        homeDiscoveryPage.verifyHomeMenuIconsDisplayed();
    }

    @When("the shopper clicks the brand tile {string}")
    public void theShopperClicksTheBrandTile(String brandTitle) {
        homeDiscoveryPage.clickBrandTile(brandTitle);
    }

    @When("the shopper clicks the category tile {string}")
    public void theShopperClicksTheCategoryTile(String categoryTitle) {
        homeDiscoveryPage.clickCategoryTile(categoryTitle);
    }

    @When("the shopper clicks the first brand tile on the home page")
    public void theShopperClicksTheFirstBrandTileOnTheHomePage() {
        homeDiscoveryPage.clickFirstBrandTile();
    }

    @When("the shopper clicks the first category tile on the home page")
    public void theShopperClicksTheFirstCategoryTileOnTheHomePage() {
        homeDiscoveryPage.clickFirstCategoryTile();
    }

    @When("the shopper focuses the search bar on the home page")
    public void theShopperFocusesTheSearchBarOnTheHomePage() {
        homeDiscoveryPage.focusSearchBar();
    }

    @Then("default search suggestions should be displayed")
    public void defaultSearchSuggestionsShouldBeDisplayed() {
        homeDiscoveryPage.verifyDefaultSearchSuggestionsDisplayed();
    }

    @Then("the product catalog page should be displayed")
    public void theProductCatalogPageShouldBeDisplayed() {
        productCatalogPage.verifyProductCatalogPageDisplayed();
    }

    @Then("the product catalog URL should include a brand filter")
    public void theProductCatalogUrlShouldIncludeABrandFilter() {
        productCatalogPage.verifyProductCatalogPageDisplayed();
        productCatalogPage.verifyUrlContainsBrandFilter();
    }

    @Then("the product catalog URL should include category filters")
    public void theProductCatalogUrlShouldIncludeCategoryFilters() {
        productCatalogPage.verifyProductCatalogPageDisplayed();
        productCatalogPage.verifyUrlContainsCategoryFilters();
    }
}
