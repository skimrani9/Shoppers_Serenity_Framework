@payment @api @integration
Feature: Payment API
  As a test engineer
  I want to validate payment service health
  So that checkout payment can proceed

  @payment @api @integration @positive @smoke
  Scenario: GET /test returns payment service health
    When the payment service health API is called
    Then the payment service should be healthy
