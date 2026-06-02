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
 * Product catalog page reached from home discovery tiles.
 */
public class ProductCatalogPage extends PageObject {

    @Step("Verify product catalog page is displayed")
    public void verifyProductCatalogPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
        wait.until(ExpectedConditions.urlContains("/products"));
    }

    @Step("Verify URL contains brand filter")
    public void verifyUrlContainsBrandFilter() {
        String url = getDriver().getCurrentUrl();
        if (!url.contains("brands=")) {
            throw new AssertionError("Expected brand filter in URL but got: " + url);
        }
    }

    @Step("Verify URL contains apparel and gender filters")
    public void verifyUrlContainsCategoryFilters() {
        String url = getDriver().getCurrentUrl();
        if (!url.contains("apparels=") || !url.contains("genders=")) {
            throw new AssertionError("Expected apparel and gender filters in URL but got: " + url);
        }
    }

    @Step("Verify URL contains search-driven filter parameters")
    public void verifyUrlContainsSearchFilter() {
        String url = getDriver().getCurrentUrl();
        if (!url.contains("/products?q=")) {
            throw new AssertionError("Expected product catalog URL from search but got: " + url);
        }
        boolean hasFilter = url.contains("brands=") || url.contains("apparels=")
                || url.contains("genders=") || url.contains("productname=");
        if (!hasFilter) {
            throw new AssertionError("Expected search filter in URL but got: " + url);
        }
    }

    @Step("Open default product catalog")
    public void openDefaultProductCatalog() {
        String baseUrl = TestConfigLoader.get("base.url");
        String query = TestConfigLoader.get("products.default.query", Constants.DEFAULT_CATALOG_QUERY);
        String root = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        openUrl(root + "/products?q=" + query);
        $("//a[contains(@href,'/products/details')]")
                .withTimeoutOf(Duration.ofSeconds(TimeoutConfig.HOME_PAGE_LOAD_SECONDS))
                .waitUntilVisible();
    }

    @Step("Open first product from catalog listing")
    public void openFirstProductFromCatalog() {
        $("//a[contains(@href,'/products/details') and .//img[@title]][1]").waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Wait for product grid to load")
    public void waitForProductGridLoaded() {
        $("//a[contains(@href,'/products/details')]")
                .withTimeoutOf(Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS))
                .waitUntilVisible();
    }
}
