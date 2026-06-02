@auth @api @integration
Feature: Authentication API
  As a test engineer
  I want to validate authentication service APIs
  So that sign-in and sign-up work before UI tests

  @auth @api @integration @positive @smoke
  Scenario: GET /test returns auth service health
    When the authentication service health API is called
    Then the authentication service should be healthy

  @auth @api @integration @positive
  Scenario: POST /signup creates a new account
    When the authentication service signup API is called with a unique user
    Then the signup API should return success

  @auth @api @integration @positive @smoke
  Scenario: POST /authenticate returns JWT for valid credentials
    When the authentication service signup API is called with a unique user
    Then the signup API should return success
    When the authentication service login API is called with valid credentials
    Then the login API should return a valid JWT

  @auth @api @integration @negative
  Scenario: POST /authenticate returns error for invalid credentials
    When the authentication service login API is called with invalid credentials
    Then the login API should return an authentication error
