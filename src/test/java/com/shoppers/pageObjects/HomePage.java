package com.shoppers.pageObjects;

import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;

/**
 * Shoppers homepage — entry point before opening the sign-in form.
 */
@DefaultUrl("${webdriver.base.url}")
public class HomePage extends PageObject {

    @Step("Open Shoppers homepage")
    public void openHomePage() {
        String baseUrl = TestConfigLoader.get("base.url");
        openUrl(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/");
    }

    @Step("Click Sign In from homepage header")
    public void clickSignInFromHeader() {
        $(LoginLocators.SIGN_IN_HEADER).waitUntilVisible().click();
    }
}
