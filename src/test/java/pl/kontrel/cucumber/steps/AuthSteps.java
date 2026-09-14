package pl.kontrel.cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.kontrel.api.AuthApi;
import pl.kontrel.context.TestContext;
import pl.kontrel.models.auth.Auth;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthSteps {

  private final TestContext testContext;
  private final AuthApi authApi;

  public AuthSteps(TestContext testContext) {
    this.testContext = testContext;
    this.authApi = new AuthApi();
  }

  @When("I ask about current user")
  public void i_ask_about_current_user() {
    testContext.setResponse(authApi.getCurrentUser(testContext.getAccessToken()));
  }

  @Then("I get a current user details")
  public void  i_get_a_current_user_details() {
    Auth authResponse = testContext.getResponse().as(Auth.class);

    assertThat(authResponse.getFirstName()).isEqualTo("Emily");
  }
}
