@search @api @integration
Feature: Search API - prefix and default suggestions
  As a test engineer
  I want to validate search suggestion APIs
  So that typeahead search works before UI tests run

  @search @api @integration @positive
  Scenario: GET /default-search-suggestion returns apparel keywords
    When the search suggestion service default suggestions API is called
    Then the default search suggestions API should return a valid list

  @search @api @integration @positive
  Scenario Outline: GET /search-suggestion returns matches for prefix
    When the search suggestion service is queried with prefix "<prefix>"
    Then the prefix search API should return matching suggestions
    And the prefix search API should include keyword containing "<expected>"

    Examples:
      | prefix | expected |
      | n      | Nike     |
      | dre    | Dresses  |
      | tsh    | Tshirts  |
      | men    | Men      |

  @search @api @integration @positive
  Scenario: GET /search-suggestion-list returns keyword source from common data
    When the common data service search suggestion list API is called
    Then the search suggestion list API should return keyword source data
