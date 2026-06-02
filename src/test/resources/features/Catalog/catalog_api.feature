@catalog @api @integration
Feature: Product Catalog API - listing and filters
  As a test engineer
  I want to validate catalog and filter APIs
  So that product listing UI has reliable data

  @catalog @api @integration @positive @smoke
  Scenario: GET /products?q= returns paginated catalog
    When the common data service catalog API is called with default pagination
    Then the catalog API should return a valid product listing

  @catalog @api @integration @positive @smoke
  Scenario: GET /filter?q= returns filter attributes
    When the common data service filter API is called with default pagination
    Then the filter API should return filter attributes
