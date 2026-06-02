@auth @multiservice @integration
Feature: Authentication multiservice integration
  As a shopper
  I need auth API aligned with sign-in UI
  So that login works end-to-end

  @auth @multiservice @integration @positive @smoke
  Scenario: Auth service healthy and UI login succeeds
    Given authentication service is healthy
    And the shopper is on the Shoppers homepage
    When the shopper logs in with username "imran" and password "1234567890"
    Then the shopper should see the dashboard
