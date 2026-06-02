@bag @multiservice @integration
Feature: Shopping Bag multiservice integration
  As a shopper
  I need bag UI backed by product lookup API
  So that cart items show live product data

  @bag @multiservice @integration @positive @smoke
  Scenario: Bag page shows product from API after add to bag
    Given product catalog and product-by-id APIs are healthy
    And the shopper has a product in the shopping bag
    When the shopper opens the shopping bag page
    Then the shopping bag should show the product from API context
