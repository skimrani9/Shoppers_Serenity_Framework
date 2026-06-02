package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.CheckoutPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Checkout & Shipping — E2E steps (Module 8).
 */
public class CheckoutUiSteps {

    @Steps
    CheckoutPage checkoutPage;

    @Given("the shopper opens the checkout page with items in bag")
    public void theShopperOpensTheCheckoutPageWithItemsInBag() {
        checkoutPage.openCheckoutPage();
        checkoutPage.verifyCheckoutPageDisplayed();
    }

    @When("the shopper submits a valid shipping address")
    public void theShopperSubmitsAValidShippingAddress() {
        checkoutPage.fillShippingAddressWithValidData();
        checkoutPage.continueShippingAddress();
    }

    @When("the shopper selects free shipping and continues")
    public void theShopperSelectsFreeShippingAndContinues() {
        checkoutPage.verifyShippingOptionsAvailable();
        checkoutPage.selectFreeShippingOption();
        checkoutPage.continueShippingOptions();
    }

    @Then("the shipping address summary should be displayed")
    public void theShippingAddressSummaryShouldBeDisplayed() {
        checkoutPage.verifyShippingAddressSummaryDisplayed();
    }

    @Then("Place Order should be enabled on checkout")
    public void placeOrderShouldBeEnabledOnCheckout() {
        checkoutPage.verifyPlaceOrderEnabled();
    }

    @Then("Place Order should be disabled on checkout")
    public void placeOrderShouldBeDisabledOnCheckout() {
        checkoutPage.verifyPlaceOrderDisabled();
    }
}
