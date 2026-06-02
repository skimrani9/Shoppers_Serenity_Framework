@search @e2e @functional
Feature: Search and Suggestions UI
  As a shopper
  I want to search with live suggestions
  So that I can find products quickly

  Background:
    Given the shopper is on the homepage with search available

  @search @e2e @functional @positive @smoke
  Scenario: Default suggestions appear when search bar is focused
    When the shopper focuses the search bar
    Then default search suggestions should appear

  @search @e2e @functional @positive
  Scenario: Live typeahead updates as the shopper types
    When the shopper focuses the search bar
    And the shopper types "dre" in the search bar
    Then typeahead search suggestions should appear
    And typeahead suggestions should include "Dresses"

  @search @e2e @functional @positive
  Scenario: Selecting a brand suggestion navigates to filtered product catalog
    When the shopper focuses the search bar
    And the shopper types "nik" in the search bar
    And the shopper selects the search suggestion "Nike"
    Then the shopper should land on the product catalog from search
    And the product catalog URL should reflect the search filter

  @search @e2e @functional @positive
  Scenario: Selecting an apparel suggestion navigates to filtered product catalog
    When the shopper focuses the search bar
    And the shopper types "tsh" in the search bar
    And the shopper selects the search suggestion "Tshirts"
    Then the shopper should land on the product catalog from search
    And the product catalog URL should reflect the search filter

  @search @e2e @functional @positive
  Scenario: Mobile search supports typeahead and navigation
    Given the shopper is on the homepage at mobile viewport
    When the shopper opens mobile search from the navbar
    And the shopper types "nik" in the search bar
    Then typeahead suggestions should include "Nike"
    When the shopper selects the search suggestion "Nike"
    Then the shopper should land on the product catalog from search

  @search @e2e @functional @positive
  Scenario Outline: Prefix typeahead returns expected keyword
    When the shopper focuses the search bar
    And the shopper types "<prefix>" in the search bar
    Then typeahead suggestions should include "<keyword>"

    Examples:
      | prefix | keyword  |
      | an     | Anouk    |
      | mang   | MANGO    |
      | kur    | Kurtas   |
      | men    | Men      |

  @search @e2e @functional @positive
  Scenario Outline: Selecting suggestion opens product catalog
    When the shopper focuses the search bar
    And the shopper types "<prefix>" in the search bar
    And the shopper selects the search suggestion "<keyword>"
    Then the shopper should land on the product catalog from search

    Examples:
      | prefix | keyword |
      | nik    | Nike    |
      | dre    | Dresses |
