Feature: Check recipes endpoint

  Scenario: Get all recipes
    When I send GET request to get recipes
    Then response status code should be 200
    And response should contain recipes
    And number of recipes should be 30

  Scenario: Get all recipes limited
    When I send GET request to get recipes with limit 10
    Then response status code should be 200
    And response should contain recipes
    And number of recipes should be 10

  Scenario Outline: Get recipe by name
    When I send GET request to get recipe by name <recipeSearchQuery>
    Then response status code should be 200
    And response should contain recipe with name <recipeName>

    Examples:
      | recipeName            | recipeSearchQuery |
      | "Greek Moussaka"      | "Moussaka"        |
      | "Japanese Ramen Soup" | "Ramen"           |
      | "Vegetarian Stir-Fry" | "Stir"            |

  Scenario: Add new recipe
    Given I have prepared a new recipe
    When I send POST request with new recipe data
    Then I can see that new recipes was added

  @authenticated
  Scenario: Delete recipe
    When I send a DELETE request with recipe id 1
    Then response should contain delete key set to true for recipe id 1
