package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.CheckoutPage;
import com.shoppers.pageObjects.ShoppingBagPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Shopping Bag — E2E UI steps (Module 6).
 */
public class ShoppingBagUiSteps {

    @Steps
    ShoppingBagPage shoppingBagPage;

    @Steps
    CheckoutPage checkoutPage;

    @Given("the shopper opens an empty shopping bag")
    public void theShopperOpensAnEmptyShoppingBag() {
        shoppingBagPage.openShoppingBagPage();
    }

    @When("the shopper opens the shopping bag page")
    public void theShopperOpensTheShoppingBagPage() {
        shoppingBagPage.openShoppingBagPage();
    }

    @When("the shopper proceeds to checkout from the bag")
    public void theShopperProceedsToCheckoutFromTheBag() {
        shoppingBagPage.clickProceedToCheckout();
    }

    @When("the shopper removes the first item from the bag")
    public void theShopperRemovesTheFirstItemFromTheBag() {
        shoppingBagPage.removeFirstItemFromBag();
    }

    @Then("the empty shopping bag state should be displayed")
    public void theEmptyShoppingBagStateShouldBeDisplayed() {
        shoppingBagPage.verifyEmptyShoppingBagDisplayed();
    }

    @Then("the shopping bag should contain items")
    public void theShoppingBagShouldContainItems() {
        shoppingBagPage.verifyShoppingBagHasItems();
    }

    @Then("the checkout page should be displayed")
    public void theCheckoutPageShouldBeDisplayed() {
        checkoutPage.verifyCheckoutPageDisplayed();
    }
}
