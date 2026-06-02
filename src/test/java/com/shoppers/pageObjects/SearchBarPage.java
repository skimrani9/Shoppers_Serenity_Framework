package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.Keys;

import java.time.Duration;

/**
 * Navbar search bar — typeahead, default suggestions, mobile search.
 */
@DefaultUrl("${webdriver.base.url}")
public class SearchBarPage extends PageObject {

    @Step("Open Shoppers homepage for search")
    public void openHomePageForSearch() {
        String baseUrl = TestConfigLoader.get("base.url");
        openUrl(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/");
        waitForHomeReadyForSearch();
    }

    @Step("Wait until homepage is ready for search interactions")
    public void waitForHomeReadyForSearch() {
        Duration loadTimeout = Duration.ofSeconds(TimeoutConfig.HOME_PAGE_LOAD_SECONDS);
        $(HomeDiscoveryLocators.TOP_BRANDS_HEADING).withTimeoutOf(loadTimeout).waitUntilVisible();
    }

    @Step("Focus the desktop search bar")
    public void focusDesktopSearchBar() {
        if ($(SearchLocators.SEARCH_INPUT).isPresent()) {
            $(SearchLocators.SEARCH_INPUT).waitUntilVisible().click();
        } else {
            $(SearchLocators.SEARCH_LABEL).waitUntilVisible().click();
        }
    }

    @Step("Open Shoppers homepage at mobile viewport for search")
    public void openHomePageAtMobileViewport() {
        getDriver().manage().window().setSize(new org.openqa.selenium.Dimension(500, 800));
        String baseUrl = TestConfigLoader.get("base.url");
        openUrl(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/");
        waitForHomeReadyForSearch();
    }

    @Step("Open mobile search from navbar")
    public void openMobileSearch() {
        $(SearchLocators.MOBILE_SEARCH_OPEN_BUTTON).withTimeoutOf(
                Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS)).waitUntilClickable().click();
        $(SearchLocators.SEARCH_INPUT).waitUntilVisible();
    }

    @Step("Type {0} in the search bar")
    public void typeInSearchBar(String text) {
        $(SearchLocators.SEARCH_INPUT).waitUntilVisible().click();
        $(SearchLocators.SEARCH_INPUT).clear();
        $(SearchLocators.SEARCH_INPUT).type(text);
        waitABit(1500);
    }

    @Step("Verify default search suggestions are displayed")
    public void verifyDefaultSearchSuggestionsDisplayed() {
        $(SearchLocators.SEARCH_SUGGESTIONS_LIST).waitUntilVisible();
        if (findAll(SearchLocators.SEARCH_SUGGESTION_OPTION).size() == 0) {
            throw new AssertionError("Expected default search suggestions in autocomplete list");
        }
    }

    @Step("Verify typeahead suggestions are displayed")
    public void verifyTypeaheadSuggestionsDisplayed() {
        $(SearchLocators.SEARCH_SUGGESTIONS_LIST).withTimeoutOf(
                Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS)).waitUntilVisible();
        if (findAll(SearchLocators.SEARCH_SUGGESTION_OPTION).size() == 0) {
            throw new AssertionError("Expected typeahead suggestions after typing a prefix");
        }
    }

    @Step("Verify typeahead includes suggestion containing {0}")
    public void verifyTypeaheadIncludesKeyword(String keywordPart) {
        verifyTypeaheadSuggestionsDisplayed();
        if (!$(SearchLocators.suggestionOptionByKeyword(keywordPart)).isPresent()) {
            throw new AssertionError("Expected suggestion containing '" + keywordPart + "'");
        }
    }

    @Step("Select search suggestion {0}")
    public void selectSearchSuggestion(String keyword) {
        $(SearchLocators.suggestionOptionByKeyword(keyword)).waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Select first visible search suggestion")
    public void selectFirstSearchSuggestion() {
        $(SearchLocators.SEARCH_SUGGESTION_OPTION).waitUntilClickable().click();
        waitABit(1000);
    }

    @Step("Submit search by pressing Enter with current input")
    public void submitSearchWithEnter() {
        $(SearchLocators.SEARCH_INPUT).sendKeys(Keys.ENTER);
        waitABit(1000);
    }
}
