package pl.kontrel.cucumber.steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pl.kontrel.api.AuthApi;
import pl.kontrel.api.PostsApi;
import pl.kontrel.context.TestContext;
import pl.kontrel.models.auth.Auth;
import pl.kontrel.models.auth.LoginRequest;
import pl.kontrel.models.posts.Posts;

import static org.assertj.core.api.Assertions.assertThat;

public class PostsSteps {

  private static final Log log = LogFactory.getLog(PostsSteps.class);
  private final TestContext testContext;
  private final PostsApi postsApi;
  private LoginRequest loginRequest;
  private final AuthApi authApi;

  public PostsSteps(TestContext testContext) {
    this.testContext = testContext;
    this.postsApi = new PostsApi();
    this.authApi = new AuthApi();
  }

  @When("I ask about all posts")
  public void iAskAboutAllPosts() {
    testContext.setResponse(postsApi.getAllPosts());
  }

  @Then("I will get all {int} posts")
  public void iWillGetAllPosts(int totalPostsNumber) {
    assertThat(testContext.getResponse().as(Posts.class).getTotal()).isEqualTo(totalPostsNumber);
  }

  @When("I ask about post with {string} word")
  public void iAskAboutPostWithWord(String searchWord) {
    testContext.setResponse(postsApi.getPostWithSearchWord(searchWord));
  }

  @Then("I get a specific post with body that contains {string}")
  public void iGetASpecificPostWithBodyThatContains(String searchedWord) {
    assertThat(testContext.getResponse().as(Posts.class).getPosts().getFirst().getBody()).contains(searchedWord);
  }

  @When("I ask about current user posts")
  public void iAskAboutCurrentUserPosts() {
    testContext.setResponse(postsApi.getCurrentUserPosts(testContext.getUserId()));
  }

  @Then("I get all posts for this user")
  public void iGetAllPostsForThisUser() {
    assertThat(testContext.getResponse().as(Posts.class)
      .getPosts()
      .stream()
      .anyMatch(post -> post.getUserId() != testContext.getUserId())
    ).isEqualTo(false);
  }

  @Given("I am logged in as {string} with password {string}")
  public void iAmLoggedInAsWithPassword(String login, String password) {
    loginRequest = new LoginRequest(login, password, 30);

    Auth auth = authApi.getAuthResponse(loginRequest);

    testContext.setUserId(auth.getId());
  }
}
