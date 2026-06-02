@home @api @integration
Feature: Home API - Common Data Service
  As a test engineer
  I want to validate the /home API
  So that home discovery data is available before UI tests run

  @home @api @integration @positive
  Scenario: GET /home returns main screen payload with brands apparels and carousels
    When the common data service home API is called
    Then the home API should return valid main screen data

  @home @api @integration @positive
  Scenario: GET /home returns non-empty brands and carousels
    When the common data service home API is called
    Then the home API brands list should not be empty
    And the home API carousels list should not be empty

  @home @api @integration @positive
  Scenario: GET /tabs returns gender navigation structure
    When the common data service tabs API is called
    Then the tabs API should return gender navigation data
