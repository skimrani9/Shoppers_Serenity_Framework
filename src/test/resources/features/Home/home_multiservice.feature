@home @multiservice @integration
Feature: Home multiservice integration
  As a shopper
  I need common-data and search-suggestion services working together
  So that the home page loads content and search hints

  @home @multiservice @integration @positive @smoke
  Scenario: Backend services are healthy before home UI load
    Given all backend services required for home discovery are healthy

  @home @multiservice @e2e @integration @positive @smoke
  Scenario: UI home page reflects data served by backend microservices
    Given all backend services required for home discovery are healthy
    And the shopper opens the Shoppers home page
    Then the home page should display the discovery layout
    When the shopper focuses the search bar on the home page
    Then default search suggestions should be displayed
