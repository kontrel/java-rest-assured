Feature: Check recipes endpoint

Scenario: Get all recipes
    When I send GET request to get recipes
    Then response status code should be 200
    And response should contain recipes
    And number of recipes should be 30