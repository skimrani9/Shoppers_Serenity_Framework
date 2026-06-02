package com.shoppers.stepdefinitions;

import com.shoppers.helpers.ScenarioContext;
import com.shoppers.pageObjects.ProductCatalogPage;
import com.shoppers.pageObjects.ProductDetailsPage;
import com.shoppers.pageObjects.ShoppingBagPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * E2E / functional UI steps for Product Details module.
 */
public class ProductDetailsUiSteps {

    @Steps
    ProductCatalogPage productCatalogPage;

    @Steps
    ProductDetailsPage productDetailsPage;

    @Steps
    ShoppingBagPage shoppingBagPage;

    @Given("the shopper opens the default product catalog")
    public void theShopperOpensTheDefaultProductCatalog() {
        productCatalogPage.openDefaultProductCatalog();
    }

    @Given("the shopper opens product details for the first catalog product via API context")
    public void theShopperOpensProductDetailsForFirstCatalogProductViaApiContext() {
        String productId = ScenarioContext.get("selectedProductId");
        productDetailsPage.openProductDetailsById(productId);
    }

    @When("the shopper opens the first product from the catalog")
    public void theShopperOpensTheFirstProductFromTheCatalog() {
        productCatalogPage.openFirstProductFromCatalog();
        productDetailsPage.waitForProductDetailsPageLoaded();
    }

    @When("the shopper increases the product quantity by {int}")
    public void theShopperIncreasesTheProductQuantityBy(int amount) {
        productDetailsPage.increaseQuantityBy(amount);
    }

    @When("the shopper adds the product to the bag")
    public void theShopperAddsTheProductToTheBag() {
        productDetailsPage.clickAddToBag();
    }

    @When("the shopper proceeds to the shopping bag from product details")
    public void theShopperProceedsToTheShoppingBagFromProductDetails() {
        productDetailsPage.clickProceedToBag();
    }

    @Then("the product details page should be displayed")
    public void theProductDetailsPageShouldBeDisplayed() {
        productDetailsPage.verifyProductDetailsPageDisplayed();
    }

    @Then("the product details should show core information")
    public void theProductDetailsShouldShowCoreInformation() {
        productDetailsPage.verifyProductImageDisplayed();
        productDetailsPage.verifyInclusiveTaxesLabelDisplayed();
        productDetailsPage.verifyBreadcrumbsIncludeDetails();
    }

    @Then("the product name from API context should be displayed on details page")
    public void theProductNameFromApiContextShouldBeDisplayedOnDetailsPage() {
        String name = ScenarioContext.get("selectedProductName");
        productDetailsPage.verifyProductNameDisplayed(name);
    }

    @Then("the shopping bag should show the product from API context")
    public void theShoppingBagShouldShowTheProductFromApiContext() {
        String name = ScenarioContext.get("selectedProductName");
        shoppingBagPage.verifyShoppingBagPageDisplayed();
        shoppingBagPage.verifyProductNameInBag(name);
    }

    @Then("the bag badge should show at least {int} item")
    public void theBagBadgeShouldShowAtLeastItem(int count) {
        productDetailsPage.verifyBagBadgeShowsAtLeast(count);
    }
}
