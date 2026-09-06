package pl.kontrel.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pl.kontrel.api.RecipesApi;
import pl.kontrel.context.TestContext;
import pl.kontrel.models.Recipe;

public class RecipesSteps {

  private final TestContext testContext;
  private final RecipesApi recipesApi;

  public RecipesSteps(TestContext testContext) {
    this.testContext = testContext;
    this.recipesApi = new RecipesApi();
  }

  @When("I send GET request to get recipes")
  public void i_send_get_request_to_get_recipes() {
    testContext.setResponse(recipesApi.getRecipes());
  }
  
  @Then("response status code should be {int}")
  public void response_status_code_should_be(Integer responseStatusCode) {
    assertEquals(responseStatusCode, testContext.getResponse().getStatusCode());
  }

  @Then("response should contain recipes")
  public void response_should_contain_recipes() {
    assertThat(testContext.getResponse().jsonPath().getList("recipes")).isNotEmpty();
  }

  @Then("number of recipes should be {int}")
  public void number_of_recipes_should_be(Integer expectedNumber) {
    int actualNumber = testContext.getResponse().jsonPath().getList("recipes").size();

    assertThat(actualNumber).isEqualTo(expectedNumber);
  }

  @When("I send GET request to get recipe by name {string}")
  public void i_send_get_request_to_get_recipe_by_name(String recipeName) {
    testContext.setResponse(recipesApi.getRecipeByName(recipeName));
  }

  @Then("response should contain recipe with name {string}")
  public void response_should_contain_recipe_with_name(String expectedName) {
    String actualName = testContext.getResponse().jsonPath().getString("recipes[0].name");

    assertThat(actualName).isEqualTo(expectedName);
  }
  
  @When("I send GET request to get recipes with limit {int}")
  public void i_send_get_request_to_get_recipes_with_limit(Integer limit) {
    testContext.setResponse(recipesApi.getRecipesWithLimit(limit));
  }

  @Given("I have prepared a new recipe")
  public void i_have_prepared_a_new_recipe() {
    Recipe recipe = new Recipe();
    recipe.setName("Pierogi");
    testContext.setRecipe(recipe);
  }

  @When("I send POST request with new recipe data")
  public void i_send_post_request_with_new_recipe_data() {
    testContext.setResponse(recipesApi.addRecipe(testContext.getRecipe()));
  }

  @Then("I can see that new recipes was added")
  public void i_can_see_that_new_recipes_was_added() {
    String addedRecipeName = testContext.getResponse().jsonPath().getString("name");
    assertThat(addedRecipeName).isEqualTo("Pierogi");
  }

  @When("I send a DELETE request with recipe id {int}")
  public void i_send_a_delete_request_with_recipe_id(Integer id) {
    testContext.setResponse(recipesApi.deleteRecipe(id));
  }

  @Then("response should contain delete key set to true for recipe id {int}")
  public void response_should_contain_delete_key_set_to_true_for_recipe_id(Integer id) {
    Response response = testContext.getResponse();

    assertThat(response.jsonPath().getInt("id")).isEqualTo(id);
    assertThat(response.jsonPath().getString("isDeleted")).isEqualTo("true");
  }
}
