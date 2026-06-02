@product @multiservice @integration
Feature: Product Details multiservice integration
  As a shopper
  I need product catalog and by-id APIs aligned with the UI
  So that product details reflect live backend data

  @product @multiservice @integration @positive @smoke
  Scenario: Product APIs are healthy before UI load
    Given product catalog and product-by-id APIs are healthy

  @product @multiservice @e2e @integration @positive @smoke
  Scenario: UI product details reflect API product data
    Given product catalog and product-by-id APIs are healthy
    And the shopper opens product details for the first catalog product via API context
    Then the product details page should be displayed
    And the product name from API context should be displayed on details page
    And the product details should show core information
