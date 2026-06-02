@bag @e2e @functional
Feature: Shopping Bag UI
  As a shopper
  I want to review and manage my bag
  So that I can proceed to checkout

  @bag @e2e @functional @positive
  Scenario: Empty shopping bag shows empty state
    Given the shopper opens an empty shopping bag
    Then the empty shopping bag state should be displayed

  @bag @e2e @functional @positive @smoke
  Scenario: Bag shows items after add to bag from product details
    Given the shopper has a product in the shopping bag
    When the shopper opens the shopping bag with items
    Then the shopping bag should contain items

  @bag @e2e @functional @positive @smoke
  Scenario: Proceed to checkout from shopping bag
    Given the shopper opens the shopping bag with items
    When the shopper proceeds to checkout from the bag
    Then the checkout page should be displayed

  @bag @e2e @functional @positive
  Scenario: Remove item from shopping bag
    Given the shopper opens the shopping bag with items
    When the shopper removes the first item from the bag
    Then the empty shopping bag state should be displayed
