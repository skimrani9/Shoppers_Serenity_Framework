package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Product catalog listing, filters, sort, and pagination.
 */
public class CatalogFilterPage extends PageObject {

    @Step("Wait for product catalog grid to load")
    public void waitForCatalogGridLoaded() {
        Duration timeout = Duration.ofSeconds(TimeoutConfig.HOME_PAGE_LOAD_SECONDS);
        if ($(CatalogLocators.NO_MATCHES).isPresent()) {
            throw new AssertionError("Catalog returned no matching products");
        }
        $(CatalogLocators.PRODUCT_GRID_LINK).withTimeoutOf(timeout).waitUntilVisible();
    }

    @Step("Select gender filter {0}")
    public void selectGenderFilter(String genderLabel) {
        $(CatalogLocators.genderRadioLabel(genderLabel)).waitUntilClickable().click();
        waitABit(1500);
    }

    @Step("Click Clear All filters")
    public void clickClearAllFilters() {
        $(CatalogLocators.CLEAR_ALL_BUTTON).waitUntilClickable().click();
        waitABit(1500);
    }

    @Step("Verify URL contains gender filter")
    public void verifyUrlContainsGenderFilter() {
        String url = getDriver().getCurrentUrl();
        if (!url.contains("genders=")) {
            throw new AssertionError("Expected gender filter in URL but got: " + url);
        }
    }

    @Step("Verify URL is default catalog pagination")
    public void verifyUrlIsDefaultCatalogPagination() {
        String url = getDriver().getCurrentUrl();
        if (!url.contains("page=0,16") || url.contains("genders=") || url.contains("brands=")) {
            throw new AssertionError("Expected default catalog URL but got: " + url);
        }
    }

    @Step("Verify filter chips are displayed")
    public void verifyFilterChipsDisplayed() {
        if (findAll(CatalogLocators.FILTER_CHIP).size() == 0) {
            throw new AssertionError("Expected active filter chips on catalog page");
        }
    }

    @Step("Open sort dropdown")
    public void openSortDropdown() {
        $(CatalogLocators.SORT_DROPDOWN).waitUntilClickable().click();
        waitABit(500);
    }

    @Step("Select sort option {0}")
    public void selectSortOption(String optionText) {
        openSortDropdown();
        $("//*[contains(@class,'menu')]//*[contains(text(),'" + optionText + "')]")
                .waitUntilClickable().click();
        waitABit(1500);
    }

    @Step("Verify URL contains sort filter")
    public void verifyUrlContainsSortFilter() {
        if (!getDriver().getCurrentUrl().contains("sortby=")) {
            throw new AssertionError("Expected sortby in catalog URL");
        }
    }

    @Step("Go to catalog page 2")
    public void goToCatalogPageTwo() {
        $(CatalogLocators.PAGINATION).waitUntilVisible();
        $(CatalogLocators.PAGINATION_PAGE_TWO).waitUntilClickable().click();
        waitABit(1500);
    }

    @Step("Verify URL contains page index 1")
    public void verifyUrlContainsPageIndexOne() {
        if (!getDriver().getCurrentUrl().contains("page=1,16")) {
            throw new AssertionError("Expected page=1,16 in URL but got: " + getDriver().getCurrentUrl());
        }
    }

    @Step("Verify product catalog page is displayed")
    public void verifyProductCatalogPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
        wait.until(ExpectedConditions.urlContains("/products"));
    }
}
