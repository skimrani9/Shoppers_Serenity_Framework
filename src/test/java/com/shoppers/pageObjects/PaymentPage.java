package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Payment success/cancel pages and Stripe checkout overlay checks.
 */
public class PaymentPage extends PageObject {

    @Step("Click Place Order to open payment")
    public void clickPlaceOrder() {
        $(CheckoutLocators.PLACE_ORDER_BUTTON).waitUntilClickable().click();
        waitABit(2000);
    }

    @Step("Verify Stripe checkout overlay is displayed")
    public void verifyStripeCheckoutOverlayDisplayed() {
        Duration timeout = Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS);
        boolean found = findAll("//iframe[contains(@name,'stripe') or contains(@src,'stripe')]").size() > 0
                || $("//*[contains(@class,'stripe') or contains(@id,'stripe')]").withTimeoutOf(timeout).isPresent();
        if (!found) {
            throw new AssertionError("Expected Stripe checkout overlay after Place Order");
        }
    }

    @Step("Verify payment success page is displayed")
    public void verifyPaymentSuccessPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.HOME_PAGE_LOAD_SECONDS));
        wait.until(ExpectedConditions.urlContains("/checkout/success-payment"));
        $("//*[contains(text(),'Payment Successful') or contains(text(),'successful')]").waitUntilVisible();
    }

    @Step("Verify payment cancel page is displayed")
    public void verifyPaymentCancelPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
        wait.until(ExpectedConditions.urlContains("/checkout/cancel-payment"));
        $("//*[contains(text(),'Payment Cancelled') or contains(text(),'Cancelled')]").waitUntilVisible();
    }
}
