package com.shoppers.stepdefinitions;

import com.shoppers.helpers.FakerData;
import com.shoppers.helpers.ScenarioContext;
import com.shoppers.helpers.TestConfigLoader;
import com.shoppers.servicehelpers.AuthApiValidator;
import com.shoppers.servicehelpers.ServiceClientFactory;
import com.shoppers.servicehelpers.ServiceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * User Authentication — API steps (Module 7).
 */
public class AuthApiSteps {

    private static final String AUTH_RESPONSE_KEY = "authApiResponse";
    private static final String SIGNUP_USER_KEY = "signupUsername";

    @When("the authentication service health API is called")
    public void theAuthenticationServiceHealthApiIsCalled() throws Exception {
        ServiceResponse response = ServiceClientFactory.authClient().getHealth();
        ScenarioContext.put(AUTH_RESPONSE_KEY, response);
    }

    @When("the authentication service signup API is called with a unique user")
    public void theAuthenticationServiceSignupApiIsCalledWithUniqueUser() throws Exception {
        String username = FakerData.uniqueUsername();
        String password = FakerData.lastPassword();
        ScenarioContext.put(SIGNUP_USER_KEY, username);
        ScenarioContext.put("signupPassword", password);
        ServiceResponse response = ServiceClientFactory.authClient().signup(
                username, password, FakerData.firstName(),
                FakerData.lastName(), FakerData.email());
        ScenarioContext.put(AUTH_RESPONSE_KEY, response);
    }

    @When("the authentication service login API is called with valid credentials")
    public void theAuthenticationServiceLoginApiIsCalledWithValidCredentials() throws Exception {
        authenticateFromConfigOrContext();
    }

    @When("the authentication service login API is called for the signed up user")
    public void theAuthenticationServiceLoginApiIsCalledForSignedUpUser() throws Exception {
        String username = ScenarioContext.get(SIGNUP_USER_KEY);
        String password = FakerData.password();
        ScenarioContext.put("signupPassword", password);
        ServiceClientFactory.authClient().signup(username, password,
                FakerData.firstName(), FakerData.lastName(), FakerData.email());
        ServiceResponse response = ServiceClientFactory.authClient().authenticate(username, password);
        ScenarioContext.put(AUTH_RESPONSE_KEY, response);
    }

    private void authenticateFromConfigOrContext() throws Exception {
        String username = ScenarioContext.get(SIGNUP_USER_KEY);
        String password = ScenarioContext.get("signupPassword");
        if (password == null) {
            password = FakerData.lastPassword();
        }
        if (username == null) {
            username = TestConfigLoader.get("login.username", "imran");
            password = TestConfigLoader.get("login.password", "1234567890");
        }
        ServiceResponse response = ServiceClientFactory.authClient().authenticate(username, password);
        ScenarioContext.put(AUTH_RESPONSE_KEY, response);
    }

    @When("the authentication service login API is called with invalid credentials")
    public void theAuthenticationServiceLoginApiIsCalledWithInvalidCredentials() throws Exception {
        ServiceResponse response = ServiceClientFactory.authClient().authenticate("invalid_user", "wrong_pass");
        ScenarioContext.put(AUTH_RESPONSE_KEY, response);
    }

    @Given("authentication service is healthy")
    public void authenticationServiceIsHealthy() throws Exception {
        ServiceResponse response = ServiceClientFactory.authClient().getHealth();
        AuthApiValidator.assertAuthServiceHealthy(response);
    }

    @Then("the authentication service should be healthy")
    public void theAuthenticationServiceShouldBeHealthy() {
        AuthApiValidator.assertAuthServiceHealthy(ScenarioContext.get(AUTH_RESPONSE_KEY));
    }

    @Then("the signup API should return success")
    public void theSignupApiShouldReturnSuccess() {
        AuthApiValidator.assertSuccessfulSignup(ScenarioContext.get(AUTH_RESPONSE_KEY));
    }

    @Then("the login API should return a valid JWT")
    public void theLoginApiShouldReturnAValidJwt() {
        AuthApiValidator.assertSuccessfulAuthentication(ScenarioContext.get(AUTH_RESPONSE_KEY));
    }

    @Then("the login API should return an authentication error")
    public void theLoginApiShouldReturnAnAuthenticationError() {
        AuthApiValidator.assertFailedAuthentication(ScenarioContext.get(AUTH_RESPONSE_KEY));
    }
}
