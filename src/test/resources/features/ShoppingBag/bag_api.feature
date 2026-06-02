@bag @api @integration
Feature: Shopping Bag API - product lookup
  As a test engineer
  I want to validate bag product lookup API
  So that cart lines resolve to product data

  @bag @api @integration @positive @smoke
  Scenario: GET /products?product_id= returns bag line items
    Given product catalog and product-by-id APIs are healthy
    When the common data service product by id API is called for bag product ids
    Then the bag product lookup API should return valid products
