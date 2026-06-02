package com.shoppers.pageObjects;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;

/**
 * Shoppers sign-in form actions and validations.
 */
public class LoginPage extends PageObject {

    @Step("Enter username {0}")
    public void enterUsername(String username) {
        $(LoginLocators.USERNAME_INPUT).waitUntilVisible().clear();
        $(LoginLocators.USERNAME_INPUT).type(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        $(LoginLocators.PASSWORD_INPUT).waitUntilVisible().clear();
        $(LoginLocators.PASSWORD_INPUT).type(password);
    }

    @Step("Click Sign In button")
    public void clickSignInButton() {
        $(LoginLocators.SIGN_IN_BUTTON).waitUntilClickable().click();
    }

    @Step("Log in with username {0}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
    }

    @Step("Verify account dashboard is displayed after successful login")
    public void verifyDashboardDisplayed() {
        $(LoginLocators.SIGN_OUT).waitUntilVisible();
        boolean visible = $(LoginLocators.SIGN_OUT).isVisible();
        if (!visible) {
            throw new AssertionError("Expected account dashboard (Sign Out) to be visible after successful login");
        }
    }

    @Step("Verify login error message is displayed")
    public void verifyLoginErrorDisplayed() {
        $(LoginLocators.LOGIN_ERROR).waitUntilVisible();
        boolean visible = $(LoginLocators.LOGIN_ERROR).isVisible();
        if (!visible) {
            throw new AssertionError("Expected login error message to be visible");
        }
    }

    @Step("Verify text is visible: {0}")
    public void verifyTextIsVisible(String text) {
        String xpath = String.format("//div[contains(text(),\"%s\")]", text);
        $(xpath).waitUntilVisible();
    }

    @Step("Wait for sign-in form to load")
    public void waitForSignInForm() {
        $(LoginLocators.USERNAME_INPUT).waitUntilVisible();
    }
}
