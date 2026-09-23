Feature: Test posts endpoint

  Scenario: Get all posts
    When I ask about all posts
    Then I will get all 251 posts

  Scenario: Get post with specified word
    When I ask about post with "love" word
    Then I get a specific post with body that contains "love"

  Scenario Outline: Get all posts for current user
    Given I am logged in as "<username>" with password "<password>"
    When I ask about current user posts
    Then I get all posts for this user

    Examples:
    | username | password     |
    | michaelw | michaelwpass |
    | sophiab  | sophiabpass  |
    | jamesd   | jamesdpass   |
