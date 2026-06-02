package com.shoppers.pageObjects;

import com.shoppers.config.TimeoutConfig;
import com.shoppers.helpers.TestConfigLoader;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Sign-up form actions and validations.
 */
public class SignUpPage extends PageObject {

    @Step("Open sign up page")
    public void openSignUpPage() {
        String baseUrl = TestConfigLoader.get("base.url");
        String root = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        openUrl(root + "/signup");
        waitForSignUpForm();
    }

    @Step("Wait for sign up form")
    public void waitForSignUpForm() {
        $(SignUpLocators.USERNAME).withTimeoutOf(Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS))
                .waitUntilVisible();
    }

    @Step("Complete sign up with generated user details")
    public void completeSignUp(String firstName, String lastName, String username,
                               String email, String password) {
        $(SignUpLocators.FIRST_NAME).type(firstName);
        $(SignUpLocators.LAST_NAME).type(lastName);
        $(SignUpLocators.USERNAME).type(username);
        $(SignUpLocators.EMAIL).type(email);
        $(SignUpLocators.PASSWORD).type(password);
        $(SignUpLocators.CONFIRM_PASSWORD).type(password);
        if ($(SignUpLocators.TERMS_CHECKBOX).isPresent()) {
            $(SignUpLocators.TERMS_CHECKBOX).click();
        } else {
            $("//span[contains(.,'Terms and Conditions')]/preceding::input[@type='checkbox'][1]").click();
        }
        $(SignUpLocators.SIGN_UP_BUTTON).waitUntilClickable().click();
        waitABit(2000);
    }

    @Step("Verify redirected to sign in page after signup")
    public void verifyRedirectedToSignInPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConfig.EXPLICIT_WAIT_SECONDS));
        wait.until(ExpectedConditions.urlContains("/signin"));
    }
}
