@checkout @e2e @functional
Feature: Checkout and Shipping UI
  As a shopper
  I want to enter shipping details and select delivery
  So that I can place an order

  @checkout @e2e @functional @positive
  Scenario: Place Order disabled before shipping steps complete
    Given the shopper completes checkout prerequisites with items in bag
    Then Place Order should be disabled on checkout

  @checkout @e2e @functional @positive @smoke
  Scenario: Submit valid shipping address
    Given the shopper completes checkout prerequisites with items in bag
    When the shopper submits a valid shipping address
    Then the shipping address summary should be displayed

  @checkout @e2e @functional @positive @smoke
  Scenario: Select shipping option enables Place Order
    Given the shopper completes checkout prerequisites with items in bag
    When the shopper submits a valid shipping address
    And the shopper selects free shipping and continues
    Then Place Order should be enabled on checkout
