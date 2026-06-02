@navigation @api @integration
Feature: Navigation API - category tabs
  As a test engineer
  I want to validate the tabs API
  So that navbar mega-menus have backend data

  @navigation @api @integration @positive @smoke
  Scenario: GET /tabs returns gender navigation structure
    When the common data service tabs API is called for navigation
    Then the navigation tabs API should return gender navigation data
