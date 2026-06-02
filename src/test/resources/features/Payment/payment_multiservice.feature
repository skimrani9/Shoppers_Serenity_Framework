@payment @multiservice @integration
Feature: Payment multiservice integration
  As a shopper
  I need payment service ready when checkout completes
  So that Place Order opens Stripe checkout

  @payment @multiservice @integration @positive @smoke
  Scenario: Checkout completes and opens Stripe overlay
    Given payment service is healthy
    And the shopper completes checkout prerequisites with items in bag
    When the shopper submits a valid shipping address
    And the shopper selects free shipping and continues
    And the shopper clicks Place Order on checkout
    Then the Stripe checkout overlay should open
