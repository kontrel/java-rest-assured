package pl.kontrel.cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pl.kontrel.api.UsersApi;
import pl.kontrel.context.TestContext;
import pl.kontrel.models.auth.Auth;
import pl.kontrel.models.users.Users;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersSteps {

  private final TestContext testContext;
  private final UsersApi usersApi;

  public UsersSteps(TestContext testContext) {
    this.testContext = testContext;
    this.usersApi = new UsersApi();
  }

  @When("I ask about current user")
  public void iAskAboutCurrentUser() {
    testContext.setResponse(usersApi.getCurrentUser(testContext.getAccessToken()));
  }

  @Then("I get a current user details")
  public void  iGetACurrentUserDetails() {
    Auth authResponse = testContext.getResponse().as(Auth.class);

    assertThat(authResponse.getFirstName()).isEqualTo("Emily");
  }

  @When("I ask about all users")
  public void iAskAboutAllUsers() {
    testContext.setResponse(usersApi.getAllUsers());
  }

  @Then("I get all users")
  public void i_get_all_users() {
    assertThat(testContext.getResponse().as(Users.class).getUsers().size()).isEqualTo(30);
  }

  @When("I ask about {int} users with fields:")
  public void iAskAboutUsersWithFields(int limit, List<String> fields) {
    testContext.setResponse(usersApi.getAllUsersWithParams(limit, String.join(",", fields)));
  }

  @Then("I get {int} users with selected fields:")
  public void iGetUsersWithSelectedFields(int limit, List<String> fields) {
    Response response = testContext.getResponse();
    JsonPath jsonPath = response.jsonPath();
    Map<String, Object> user = jsonPath.getMap("users[0]");

    assertThat(user.keySet()).containsExactlyInAnyOrderElementsOf(fields);
    assertThat(response.as(Users.class).getUsers().size()).isEqualTo(limit);
  }
}
