@product @e2e @functional
Feature: Product Details UI
  As a shopper
  I want to view product information and add items to my bag
  So that I can continue shopping or checkout

  @product @e2e @functional @positive @smoke
  Scenario: Shopper navigates from catalog to product details
    Given the shopper opens the default product catalog
    When the shopper opens the first product from the catalog
    Then the product details page should be displayed
    And the product details should show core information

  @product @e2e @functional @positive
  Scenario: Shopper increases quantity on product details
    Given the shopper opens the default product catalog
    When the shopper opens the first product from the catalog
    And the shopper increases the product quantity by 2
    Then the product details page should be displayed

  @product @e2e @functional @positive @smoke
  Scenario: Shopper adds product to bag from details page
    Given the shopper opens the default product catalog
    When the shopper opens the first product from the catalog
    And the shopper adds the product to the bag
    Then the bag badge should show at least 1 item

  @product @e2e @functional @positive
  Scenario: Shopper proceeds to shopping bag after add to bag
    Given product catalog and product-by-id APIs are healthy
    And the shopper opens product details for the first catalog product via API context
    When the shopper adds the product to the bag
    And the shopper proceeds to the shopping bag from product details
    Then the shopping bag should show the product from API context
