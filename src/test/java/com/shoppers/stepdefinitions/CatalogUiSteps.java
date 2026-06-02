package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.CatalogFilterPage;
import com.shoppers.pageObjects.ProductCatalogPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Product Catalog & Filtering — E2E UI steps (Module 4).
 */
public class CatalogUiSteps {

    @Steps
    ProductCatalogPage productCatalogPage;

    @Steps
    CatalogFilterPage catalogFilterPage;

    @Given("the shopper opens the default product catalog for filtering")
    public void theShopperOpensTheDefaultProductCatalogForFiltering() {
        productCatalogPage.openDefaultProductCatalog();
        catalogFilterPage.waitForCatalogGridLoaded();
    }

    @When("the shopper selects the {string} gender filter")
    public void theShopperSelectsTheGenderFilter(String gender) {
        catalogFilterPage.selectGenderFilter(gender);
    }

    @When("the shopper clears all catalog filters")
    public void theShopperClearsAllCatalogFilters() {
        catalogFilterPage.clickClearAllFilters();
    }

    @When("the shopper selects catalog sort option {string}")
    public void theShopperSelectsCatalogSortOption(String option) {
        catalogFilterPage.selectSortOption(option);
    }

    @When("the shopper navigates to catalog page 2")
    public void theShopperNavigatesToCatalogPageTwo() {
        catalogFilterPage.goToCatalogPageTwo();
    }

    @Then("the product catalog grid should be displayed")
    public void theProductCatalogGridShouldBeDisplayed() {
        catalogFilterPage.verifyProductCatalogPageDisplayed();
        catalogFilterPage.waitForCatalogGridLoaded();
    }

    @Then("the catalog URL should include a gender filter")
    public void theCatalogUrlShouldIncludeAGenderFilter() {
        catalogFilterPage.verifyUrlContainsGenderFilter();
    }

    @Then("the catalog URL should be default pagination")
    public void theCatalogUrlShouldBeDefaultPagination() {
        catalogFilterPage.verifyUrlIsDefaultCatalogPagination();
    }

    @Then("the catalog URL should include a sort filter")
    public void theCatalogUrlShouldIncludeASortFilter() {
        catalogFilterPage.verifyUrlContainsSortFilter();
    }

    @Then("the catalog URL should show page 2")
    public void theCatalogUrlShouldShowPageTwo() {
        catalogFilterPage.verifyUrlContainsPageIndexOne();
    }
}
