@login
Feature: Shoppers user login
  As a shopper
  I want to sign in from the homepage
  So that I can access my account

  Background:
    Given the shopper is on the Shoppers homepage

  @login @positive
  Scenario: Successful login with valid credentials
    When the shopper logs in with username "imran" and password "1234567890"
    Then the shopper should see the dashboard

  @login @negative
  Scenario: Login fails when username does not exist
    When the shopper logs in with username "invalid_user" and password "wrong_pass"
    Then the shopper should see a login error message

  @login @negative
  Scenario: Login fails with invalid username and blank password
    When the shopper logs in with username "unknown_user" and password ""
    Then the shopper should see a login error message

  @login
  Scenario Outline: Login with test data from examples
    When the shopper logs in with username "<username>" and password "<password>"
    Then the shopper should see "<expected_element>"

    Examples:
      | username      | password    | expected_element                         |
      | imran         | 1234567890   | dashboard                               |
      | invalid_user  | wrong_pass  | Error: Username does not exist.          |
