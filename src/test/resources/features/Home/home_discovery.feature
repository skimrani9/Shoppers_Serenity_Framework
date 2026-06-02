@home @e2e @functional
Feature: Home and Discovery UI
  As a shopper
  I want to explore the home page
  So that I can discover brands and categories

  Background:
    Given the shopper opens the Shoppers home page

  @home @e2e @functional @positive @smoke
  Scenario: Home page displays hero carousel and discovery sections
    Then the home page should display the discovery layout

  @home @e2e @functional @positive
  Scenario: Shop Top Brands section displays clickable brand tiles
    Then the Shop Top Brands section should show brand tiles

  @home @e2e @functional @positive
  Scenario: Shop Top Categories section displays clickable category tiles
    Then the Shop Top Categories section should show category tiles

  @home @e2e @functional @positive
  Scenario: Hero carousel is visible on the home page
    Then the hero carousel should be visible on the home page

  @home @e2e @functional @positive
  Scenario: Home menu quick-link icons are visible
    Then home menu quick-link icons should be visible

  @home @e2e @functional @positive
  Scenario: Default search suggestions appear when focusing the search bar
    When the shopper focuses the search bar on the home page
    Then default search suggestions should be displayed

  @home @e2e @functional @positive
  Scenario: Clicking a brand tile navigates to the product catalog with brand filter
    When the shopper clicks the brand tile "Nike"
    Then the product catalog URL should include a brand filter

  @home @e2e @functional @positive
  Scenario: Clicking a category tile navigates to the product catalog with category filters
    When the shopper clicks the category tile "Dresses"
    Then the product catalog URL should include category filters

  @home @e2e @functional @positive
  Scenario: Clicking the first brand tile opens the product catalog
    When the shopper clicks the first brand tile on the home page
    Then the product catalog page should be displayed

  @home @e2e @functional @positive
  Scenario Outline: Brand tile navigation from home discovery
    When the shopper clicks the brand tile "<brand>"
    Then the product catalog page should be displayed

    Examples:
      | brand  |
      | Nike   |
      | Anouk  |
      | Mango  |

  @home @e2e @functional @positive
  Scenario Outline: Category tile navigation from home discovery
    When the shopper clicks the category tile "<category>"
    Then the product catalog URL should include category filters

    Examples:
      | category |
      | Dresses  |
      | Tshirts  |
      | Tops     |
