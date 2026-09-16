Feature: Check auth endpoint

  @authenticated
  Scenario: Get current user
    When I ask about current user
    Then I get a current user details

  Scenario: Get all users
    When I ask about all users
    Then I get all users

  Scenario: Get limited amount of users with selected fields
    When I ask about 10 users with fields:
      |firstName|
      |lastName |
      |age      |
    Then I get 10 users with selected fields:
      |id       |
      |firstName|
      |lastName |
      |age      |