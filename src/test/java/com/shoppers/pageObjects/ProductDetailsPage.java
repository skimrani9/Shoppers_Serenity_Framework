package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.constants.Constants;
import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Product Details page — image, info, quantity, add to bag.
 */
public class ProductDetailsPage extends PageObject {

    @Step("Open product details for id {0}")
    public void openProductDetailsById(String productId) {
        String baseUrl = TestConfigLoader.get("base.url");
        String catalogQuery = TestConfigLoader.get("products.default.query", Constants.DEFAULT_CATALOG_QUERY);
        String url = String.format("%s/products/details?q=%s::product_id=%s",
                baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl,
                catalogQuery, productId);
        openUrl(url);
        waitForProductDetailsPageLoaded();
    }

    @Step("Wait for product details page to load")
    public void waitForProductDetailsPageLoaded() {
        Duration timeout = Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS);
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.urlContains(ProductDetailsLocators.DETAILS_PATH_FRAGMENT));
        $(ProductDetailsLocators.ADD_TO_BAG_BUTTON).withTimeoutOf(timeout).waitUntilVisible();
    }

    @Step("Verify product details page is displayed")
    public void verifyProductDetailsPageDisplayed() {
        String url = getDriver().getCurrentUrl();
        if (!url.contains(ProductDetailsLocators.DETAILS_PATH_FRAGMENT)
                || !url.contains(ProductDetailsLocators.PRODUCT_ID_PARAM)) {
            throw new AssertionError("Expected product details URL but got: " + url);
        }
        $(ProductDetailsLocators.ADD_TO_BAG_BUTTON).waitUntilVisible();
    }

    @Step("Verify product name {0} is displayed on details page")
    public void verifyProductNameDisplayed(String productName) {
        $(ProductDetailsLocators.productNameText(productName)).waitUntilVisible();
    }

    @Step("Verify product image is displayed")
    public void verifyProductImageDisplayed() {
        String name = $(ProductDetailsLocators.PRODUCT_IMAGE).isPresent()
                ? $(ProductDetailsLocators.PRODUCT_IMAGE).getAttribute("title")
                : null;
        if (name == null || name.isBlank()) {
            if (findAll("//img[@title]").size() == 0) {
                throw new AssertionError("Expected product image on details page");
            }
        }
    }

    @Step("Verify inclusive taxes label is displayed")
    public void verifyInclusiveTaxesLabelDisplayed() {
        $(ProductDetailsLocators.INCLUSIVE_TAXES_TEXT).waitUntilVisible();
    }

    @Step("Verify breadcrumbs include Details")
    public void verifyBreadcrumbsIncludeDetails() {
        $(ProductDetailsLocators.BREADCRUMB_NAV).waitUntilVisible();
        $(ProductDetailsLocators.BREADCRUMB_HOME).waitUntilVisible();
        $(ProductDetailsLocators.BREADCRUMB_PRODUCTS).waitUntilVisible();
    }

    @Step("Increase product quantity by {0}")
    public void increaseQuantityBy(int times) {
        for (int i = 0; i < times; i++) {
            $(ProductDetailsLocators.QTY_INCREASE_BUTTON).waitUntilEnabled().click();
            waitABit(300);
        }
    }

    @Step("Click Add to Bag")
    public void clickAddToBag() {
        $(ProductDetailsLocators.ADD_TO_BAG_BUTTON).waitUntilClickable().click();
        waitABit(500);
    }

    @Step("Click Proceed to Bag")
    public void clickProceedToBag() {
        $(ProductDetailsLocators.PROCEED_TO_BAG_BUTTON).waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Verify bag badge shows at least {0} item(s)")
    public void verifyBagBadgeShowsAtLeast(int minCount) {
        $(ProductDetailsLocators.BAG_BADGE_COUNT).waitUntilVisible();
        String text = $(ProductDetailsLocators.BAG_BADGE_COUNT).getText();
        try {
            if (Integer.parseInt(text.trim()) < minCount) {
                throw new AssertionError("Expected bag count >= " + minCount + " but was " + text);
            }
        } catch (NumberFormatException e) {
            throw new AssertionError("Unable to parse bag badge count: " + text);
        }
    }
}
