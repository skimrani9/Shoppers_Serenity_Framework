@search @multiservice @integration
Feature: Search multiservice integration
  As a shopper
  I need common-data and search-suggestion services aligned
  So that prefix search reflects catalog keyword data

  @search @multiservice @integration @positive @smoke
  Scenario: Search services are healthy and prefix index is ready
    Given search microservices are healthy and indexed

  @search @multiservice @e2e @integration @positive @smoke
  Scenario: UI search typeahead uses live search-suggestion service
    Given search microservices are healthy and indexed
    And the shopper is on the homepage with search available
    When the shopper focuses the search bar
    Then default search suggestions should appear
    When the shopper types "nik" in the search bar
    Then typeahead suggestions should include "Nike"
    When the shopper selects the search suggestion "Nike"
    Then the shopper should land on the product catalog from search
    And the product catalog URL should reflect the search filter
