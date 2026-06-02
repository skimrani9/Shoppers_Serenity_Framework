package com.shoppers.stepdefinitions;

import com.shoppers.helpers.FakerData;
import com.shoppers.helpers.ScenarioContext;
import com.shoppers.pageObjects.SignUpPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

/**
 * Sign Up — E2E UI steps (Module 7).
 */
public class SignUpUiSteps {

    @Steps
    SignUpPage signUpPage;

    @Given("the shopper opens the sign up page")
    public void theShopperOpensTheSignUpPage() {
        signUpPage.openSignUpPage();
    }

    @When("the shopper registers with unique credentials")
    public void theShopperRegistersWithUniqueCredentials() {
        String username = FakerData.uniqueUsername();
        String password = FakerData.password();
        ScenarioContext.put("signupUsername", username);
        ScenarioContext.put("signupPassword", password);
        signUpPage.completeSignUp(
                FakerData.firstName(), FakerData.lastName(),
                username, FakerData.email(), password);
    }

    @Then("the shopper should be redirected to the sign in page")
    public void theShopperShouldBeRedirectedToTheSignInPage() {
        signUpPage.verifyRedirectedToSignInPage();
    }
}
