@navigation @e2e @functional
Feature: Navigation and Category Tabs UI
  As a shopper
  I want navbar tabs and mega-menus
  So that I can browse by gender and category

  @navigation @e2e @functional @positive @smoke
  Scenario: Desktop navbar shows gender tabs
    Given the shopper opens the homepage for desktop navigation
    Then navbar gender tabs should be displayed

  @navigation @e2e @functional @positive @smoke
  Scenario: Hovering a tab opens mega-menu and navigates to catalog
    Given the shopper opens the homepage for desktop navigation
    When the shopper hovers the "MEN" navbar tab
    Then the category mega-menu should be displayed
    When the shopper selects the first brand from the mega-menu
    Then the shopper should land on a filtered product catalog

  @navigation @e2e @functional @positive
  Scenario: Mobile navigation drawer opens from hamburger menu
    When the shopper opens the mobile navigation menu
    Then the mobile navigation drawer should be displayed
