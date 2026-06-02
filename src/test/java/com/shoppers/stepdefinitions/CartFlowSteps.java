package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.ProductCatalogPage;
import com.shoppers.pageObjects.ProductDetailsPage;
import com.shoppers.pageObjects.ShoppingBagPage;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;

/**
 * Shared cart setup steps used across bag, checkout, and payment modules.
 */
public class CartFlowSteps {

    @Steps
    ProductCatalogPage productCatalogPage;

    @Steps
    ProductDetailsPage productDetailsPage;

    @Steps
    ShoppingBagPage shoppingBagPage;

    @Given("the shopper has a product in the shopping bag")
    public void theShopperHasAProductInTheShoppingBag() {
        productCatalogPage.openDefaultProductCatalog();
        productCatalogPage.openFirstProductFromCatalog();
        productDetailsPage.waitForProductDetailsPageLoaded();
        productDetailsPage.clickAddToBag();
        waitABit();
    }

    @Given("the shopper opens the shopping bag with items")
    public void theShopperOpensTheShoppingBagWithItems() {
        theShopperHasAProductInTheShoppingBag();
        shoppingBagPage.openShoppingBagPage();
        shoppingBagPage.verifyShoppingBagHasItems();
    }

    @Given("the shopper completes checkout prerequisites with items in bag")
    public void theShopperCompletesCheckoutPrerequisitesWithItemsInBag() {
        theShopperOpensTheShoppingBagWithItems();
        shoppingBagPage.clickProceedToCheckout();
    }

    private void waitABit() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
