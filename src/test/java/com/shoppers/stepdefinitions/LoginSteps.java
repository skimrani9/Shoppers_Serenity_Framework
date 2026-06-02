package com.shoppers.stepdefinitions;

import com.shoppers.pageObjects.HomePage;
import com.shoppers.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Step definitions for Shoppers login scenarios.
 */
public class LoginSteps {

    @Steps
    HomePage homePage;

    @Steps
    LoginPage loginPage;

    @Given("the shopper is on the Shoppers homepage")
    public void theShopperIsOnTheShoppersHomepage() {
        homePage.openHomePage();
    }

    @When("the shopper opens the sign in form from the homepage")
    public void theShopperOpensTheSignInFormFromTheHomepage() {
        homePage.clickSignInFromHeader();
        loginPage.waitForSignInForm();
    }

    @When("the shopper logs in with username {string} and password {string}")
    public void theShopperLogsInWithUsernameAndPassword(String username, String password) {
        homePage.clickSignInFromHeader();
        loginPage.waitForSignInForm();
        loginPage.login(username, password);
    }

    @When("the shopper signs in with username {string} and password {string}")
    public void theShopperSignsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the shopper should see the dashboard")
    public void theShopperShouldSeeTheDashboard() {
        loginPage.verifyDashboardDisplayed();
    }

    @Then("the shopper should see a login error message")
    public void theShopperShouldSeeALoginErrorMessage() {
        loginPage.verifyLoginErrorDisplayed();
    }

    @Then("the shopper should see {string}")
    public void theShopperShouldSee(String expectedText) {
        if ("dashboard".equalsIgnoreCase(expectedText)) {
            loginPage.verifyDashboardDisplayed();
        } else if (expectedText.startsWith("Error:")) {
            loginPage.verifyLoginErrorDisplayed();
        } else {
            loginPage.verifyTextIsVisible(expectedText);
        }
    }
}
