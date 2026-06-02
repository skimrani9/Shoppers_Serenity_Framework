@catalog @multiservice @integration
Feature: Catalog multiservice integration
  As a shopper
  I need catalog and filter APIs aligned with the UI
  So that filtered listings reflect backend data

  @catalog @multiservice @integration @positive @smoke
  Scenario: Catalog APIs are healthy before UI load
    Given catalog and filter APIs are healthy

  @catalog @multiservice @e2e @integration @positive @smoke
  Scenario: UI catalog grid reflects API product data
    Given catalog and filter APIs are healthy
    And the shopper opens the default product catalog for filtering
    Then the product catalog grid should be displayed
