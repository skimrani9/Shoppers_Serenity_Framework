package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Home & Discovery page — carousel, brands, categories, search bar.
 */
@DefaultUrl("${webdriver.base.url}")
public class HomeDiscoveryPage extends PageObject {

    @Step("Open Shoppers homepage")
    public void openHomePage() {
        String baseUrl = TestConfigLoader.get("base.url");
        openUrl(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/");
    }

    @Step("Wait for home page content to load")
    public void waitForHomePageLoaded() {
        Duration loadTimeout = Duration.ofSeconds(TimeoutConfig.HOME_PAGE_LOAD_SECONDS);
        $(HomeDiscoveryLocators.TOP_BRANDS_HEADING).withTimeoutOf(loadTimeout).waitUntilVisible();
        $(HomeDiscoveryLocators.TOP_CATEGORIES_HEADING).withTimeoutOf(loadTimeout).waitUntilVisible();
    }

    @Step("Verify home page title contains Shoppers")
    public void verifyHomePageTitle() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
        wait.until(driver -> driver.getTitle() != null && driver.getTitle().toLowerCase().contains("shoppers"));
    }

    @Step("Verify hero carousel is displayed")
    public void verifyHeroCarouselDisplayed() {
        $(HomeDiscoveryLocators.CAROUSEL_CONTAINER).waitUntilVisible();
        if (findAll(HomeDiscoveryLocators.CAROUSEL_IMAGES).size() == 0) {
            throw new AssertionError("Expected carousel images on home page");
        }
    }

    @Step("Verify Shop Top Brands section is displayed")
    public void verifyTopBrandsSectionDisplayed() {
        $(HomeDiscoveryLocators.TOP_BRANDS_HEADING).waitUntilVisible();
        if (findAll(HomeDiscoveryLocators.BRAND_TILES).size() == 0) {
            throw new AssertionError("Expected brand tiles under Shop Top Brands");
        }
    }

    @Step("Verify Shop Top Categories section is displayed")
    public void verifyTopCategoriesSectionDisplayed() {
        $(HomeDiscoveryLocators.TOP_CATEGORIES_HEADING).waitUntilVisible();
        if (findAll(HomeDiscoveryLocators.CATEGORY_TILES).size() == 0) {
            throw new AssertionError("Expected category tiles under Shop Top Categories");
        }
    }

    @Step("Verify home menu icons are displayed")
    public void verifyHomeMenuIconsDisplayed() {
        // HomeMenuIcons is hidden at xs and lg breakpoints; use md viewport (~1100px).
        getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(1100, 900));
        waitABit(1000);
        if (findAll(HomeDiscoveryLocators.HOME_MENU_ICONS).size() == 0) {
            throw new AssertionError("Expected home menu icon links on home page (viewport 1100x900 / md breakpoint)");
        }
    }

    @Step("Click brand tile {0}")
    public void clickBrandTile(String brandTitle) {
        $(HomeDiscoveryLocators.brandTileByTitle(brandTitle)).waitUntilClickable().click();
    }

    @Step("Click category tile {0}")
    public void clickCategoryTile(String categoryTitle) {
        $(HomeDiscoveryLocators.categoryTileByTitle(categoryTitle)).waitUntilClickable().click();
    }

    @Step("Click first brand tile on home page")
    public void clickFirstBrandTile() {
        $(HomeDiscoveryLocators.BRAND_TILES).waitUntilClickable().click();
    }

    @Step("Click first category tile on home page")
    public void clickFirstCategoryTile() {
        $(HomeDiscoveryLocators.CATEGORY_TILES).waitUntilClickable().click();
    }

    @Step("Focus search bar on home page")
    public void focusSearchBar() {
        if ($(HomeDiscoveryLocators.SEARCH_INPUT).isPresent()) {
            $(HomeDiscoveryLocators.SEARCH_INPUT).waitUntilVisible().click();
        } else {
            $(HomeDiscoveryLocators.SEARCH_LABEL).waitUntilVisible().click();
        }
    }

    @Step("Verify default search suggestions are displayed")
    public void verifyDefaultSearchSuggestionsDisplayed() {
        $(HomeDiscoveryLocators.SEARCH_SUGGESTIONS_LIST).waitUntilVisible();
        if (findAll(HomeDiscoveryLocators.SEARCH_SUGGESTION_OPTION).size() == 0) {
            throw new AssertionError("Expected default search suggestions in autocomplete list");
        }
    }
}
