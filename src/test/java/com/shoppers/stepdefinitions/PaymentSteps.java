package com.shoppers.stepdefinitions;

import com.shoppers.helpers.ScenarioContext;
import com.shoppers.pageObjects.CheckoutPage;
import com.shoppers.pageObjects.PaymentPage;
import com.shoppers.servicehelpers.PaymentApiValidator;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Payment & Order Confirmation — API and E2E steps (Module 9).
 */
public class PaymentSteps {

    private static final String PAYMENT_HEALTH_KEY = "paymentHealthResponse";

    @Steps
    CheckoutPage checkoutPage;

    @Steps
    PaymentPage paymentPage;

    @When("the payment service health API is called")
    public void thePaymentServiceHealthApiIsCalled() throws Exception {
        ServiceResponse response = ServiceClientFactory.paymentClient().getHealth();
        ScenarioContext.put(PAYMENT_HEALTH_KEY, response);
    }

    @Given("payment service is healthy")
    public void paymentServiceIsHealthy() throws Exception {
        ServiceResponse response = ServiceClientFactory.paymentClient().getHealth();
        PaymentApiValidator.assertPaymentServiceHealthy(response);
    }

    @Then("the payment service should be healthy")
    public void thePaymentServiceShouldBeHealthy() {
        PaymentApiValidator.assertPaymentServiceHealthy(ScenarioContext.get(PAYMENT_HEALTH_KEY));
    }

    @When("the shopper clicks Place Order on checkout")
    public void theShopperClicksPlaceOrderOnCheckout() {
        checkoutPage.verifyPlaceOrderEnabled();
        paymentPage.clickPlaceOrder();
    }

    @Then("the Stripe checkout overlay should open")
    public void theStripeCheckoutOverlayShouldOpen() {
        paymentPage.verifyStripeCheckoutOverlayDisplayed();
    }
}
