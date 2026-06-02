@catalog @e2e @functional
Feature: Product Catalog and Filtering UI
  As a shopper
  I want to filter, sort, and paginate products
  So that I can find items in the catalog

  @catalog @e2e @functional @positive @smoke
  Scenario: Default product catalog loads with product grid
    Given the shopper opens the default product catalog for filtering
    Then the product catalog grid should be displayed

  @catalog @e2e @functional @positive
  Scenario: Applying gender filter updates catalog URL
    Given the shopper opens the default product catalog for filtering
    When the shopper selects the "Men" gender filter
    Then the catalog URL should include a gender filter
    And the product catalog grid should be displayed

  @catalog @e2e @functional @positive
  Scenario: Clear All resets catalog to default pagination
    Given the shopper opens the default product catalog for filtering
    When the shopper selects the "Men" gender filter
    And the shopper clears all catalog filters
    Then the catalog URL should be default pagination

  @catalog @e2e @functional @positive
  Scenario: Pagination navigates to page 2
    Given the shopper opens the default product catalog for filtering
    When the shopper navigates to catalog page 2
    Then the catalog URL should show page 2
