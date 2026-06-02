@navigation @multiservice @integration
Feature: Navigation multiservice integration
  As a shopper
  I need tabs API data aligned with navbar UI
  So that category navigation works end-to-end

  @navigation @multiservice @integration @positive @smoke
  Scenario: Tabs API is healthy before navbar UI load
    Given navigation tabs API is healthy

  @navigation @multiservice @e2e @integration @positive @smoke
  Scenario: Navbar tabs reflect live tabs API data
    Given navigation tabs API is healthy
    And the shopper opens the homepage for desktop navigation
    Then navbar gender tabs should be displayed
    And Sign In should be visible in the navbar
    And the shopping bag icon should be visible in the navbar
