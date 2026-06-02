@product @api @integration
Feature: Product Details API - catalog and by-id
  As a test engineer
  I want to validate common-data product APIs
  So that product details UI has reliable backend data

  @product @api @integration @positive @smoke
  Scenario: GET /products?q= returns paginated product list
    When the common data service product catalog API is called with default pagination
    Then the product catalog API should return a valid product list

  @product @api @integration @positive @smoke
  Scenario: GET /products?product_id= returns product matching catalog list
    When the common data service product catalog API is called with default pagination
    And the common data service product by id API is called for the first catalog product
    Then the product catalog API should return a valid product list
    And the product by id API should return matching product details
