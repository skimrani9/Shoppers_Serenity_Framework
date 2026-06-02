@home @api @integration
Feature: Home API - Search Suggestion Service
  As a test engineer
  I want to validate default search suggestions API
  So that the home page search bar can load hints on focus

  @home @api @integration @positive
  Scenario: GET /default-search-suggestion returns suggestion keywords
    When the search suggestion service default suggestions API is called
    Then the default search suggestions API should return a valid list
