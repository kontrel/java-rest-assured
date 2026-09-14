Feature: Check auth endpoint

  @authenticated
  Scenario: Get current user
    When I ask about current user
    Then I get a current user details