@signup @e2e @functional
Feature: Sign Up UI
  As a new shopper
  I want to register an account
  So that I can sign in and shop

  @signup @e2e @functional @positive @smoke
  Scenario: Successful registration redirects to sign in
    Given the shopper opens the sign up page
    When the shopper registers with unique credentials
    Then the shopper should be redirected to the sign in page
