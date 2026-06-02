package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.helpers.FakerData;
import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Checkout flow — shipping address, shipping options, place order readiness.
 */
public class CheckoutPage extends PageObject {

    @Step("Open checkout page")
    public void openCheckoutPage() {
        String baseUrl = TestConfigLoader.get("base.url");
        String root = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        openUrl(root + "/checkout");
        waitABit(2000);
    }

    @Step("Verify checkout page is displayed")
    public void verifyCheckoutPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
        wait.until(ExpectedConditions.urlContains("/checkout"));
        $(CheckoutLocators.SHIPPING_ADDRESS_HEADER).waitUntilVisible();
    }

    @Step("Fill shipping address with valid test data")
    public void fillShippingAddressWithValidData() {
        $(CheckoutLocators.FIRST_NAME).type(FakerData.firstName());
        $(CheckoutLocators.LAST_NAME).type(FakerData.lastName());
        $(CheckoutLocators.EMAIL).type(FakerData.email());
        $(CheckoutLocators.ADDRESS_LINE1).type(FakerData.streetAddress());
        $(CheckoutLocators.CITY).type("New York");
        $(CheckoutLocators.ZIP_CODE).type("10001");
        $(CheckoutLocators.PHONE).type("2125551234");
        selectStateCode("NY");
    }

    @Step("Select state code {0}")
    public void selectStateCode(String stateCode) {
        $("//*[@name='stateCode']").waitUntilClickable().click();
        waitABit(300);
        $("//*[@role='listbox']//*[text()='" + stateCode + "']").waitUntilClickable().click();
    }

    @Step("Continue shipping address step")
    public void continueShippingAddress() {
        $(CheckoutLocators.CONTINUE_BUTTON).waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Select free shipping option")
    public void selectFreeShippingOption() {
        $(CheckoutLocators.FREE_SHIPPING_RADIO).waitUntilClickable().click();
        waitABit(500);
    }

    @Step("Continue shipping options step")
    public void continueShippingOptions() {
        $(CheckoutLocators.CONTINUE_BUTTON).waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Verify shipping address summary is displayed")
    public void verifyShippingAddressSummaryDisplayed() {
        $(CheckoutLocators.SHIPPING_SUMMARY).waitUntilVisible();
    }

    @Step("Verify shipping options section is available")
    public void verifyShippingOptionsAvailable() {
        $(CheckoutLocators.SHIPPING_OPTIONS_HEADER).waitUntilVisible();
    }

    @Step("Verify Place Order button is enabled")
    public void verifyPlaceOrderEnabled() {
        if (!$(CheckoutLocators.PLACE_ORDER_BUTTON).isEnabled()) {
            throw new AssertionError("Expected PLACE ORDER button to be enabled");
        }
    }

    @Step("Verify Place Order button is disabled")
    public void verifyPlaceOrderDisabled() {
        if ($(CheckoutLocators.PLACE_ORDER_BUTTON).isEnabled()) {
            throw new AssertionError("Expected PLACE ORDER button to be disabled");
        }
    }
}
