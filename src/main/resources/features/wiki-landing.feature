Feature: Wikipedia landing page navigation

  @UI-Test
  Scenario: Wikipedia by selected language
    Given a user is on the landing page of "Wikipedia"
    When the "English" language link is selected
    Then the user will be presented with the "English" language landing page of Wikipedia