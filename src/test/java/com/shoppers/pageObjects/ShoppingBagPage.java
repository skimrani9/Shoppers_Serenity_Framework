package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Shopping bag page — view items, qty, remove, proceed to checkout.
 */
public class ShoppingBagPage extends PageObject {

    @Step("Open shopping bag page")
    public void openShoppingBagPage() {
        String baseUrl = TestConfigLoader.get("base.url");
        String root = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        openUrl(root + "/shopping-bag");
        waitABit(1500);
    }

    @Step("Verify shopping bag page is displayed")
    public void verifyShoppingBagPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
        wait.until(ExpectedConditions.urlContains("/shopping-bag"));
    }

    @Step("Verify product {0} appears in shopping bag")
    public void verifyProductNameInBag(String productName) {
        $(ProductDetailsLocators.productNameText(productName)).waitUntilVisible();
    }

    @Step("Verify shopping bag has items")
    public void verifyShoppingBagHasItems() {
        $(ShoppingBagLocators.BAG_HEADER).waitUntilVisible();
    }

    @Step("Verify empty shopping bag state is displayed")
    public void verifyEmptyShoppingBagDisplayed() {
        if (!$(ShoppingBagLocators.WANNA_SHOP_BUTTON).isPresent()
                && !$(ShoppingBagLocators.EMPTY_BAG_MESSAGE).isPresent()) {
            throw new AssertionError("Expected empty shopping bag state");
        }
    }

    @Step("Click Proceed To Checkout")
    public void clickProceedToCheckout() {
        $(ShoppingBagLocators.PROCEED_TO_CHECKOUT).waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Remove first item from shopping bag")
    public void removeFirstItemFromBag() {
        $(ShoppingBagLocators.REMOVE_BUTTON).waitUntilClickable().click();
        waitABit(500);
        if ($(ShoppingBagLocators.CONFIRM_MODAL_YES).isPresent()) {
            $(ShoppingBagLocators.CONFIRM_MODAL_YES).waitUntilClickable().click();
            waitABit(1000);
        }
    }

    @Step("Open shopping bag from navbar")
    public void openShoppingBagFromNavbar() {
        $("//*[contains(@class,'MuiBadge-root')]/ancestor::button | //a[contains(@href,'shopping-bag')]")
                .waitUntilClickable().click();
        waitABit(1500);
    }
}
