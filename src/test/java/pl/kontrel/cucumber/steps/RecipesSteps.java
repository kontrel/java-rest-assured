package pl.kontrel.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pl.kontrel.api.RecipesApi;

public class RecipesSteps {
    
  Response response;
  private final RecipesApi recipesApi = new RecipesApi();

  @When("I send GET request to get recipes")
  public void i_send_get_request_to_get_recipes() {
    response = recipesApi.getRecipes();
  }
  
  @Then("response status code should be {int}")
  public void response_status_code_should_be(Integer int1) {
    assertEquals(int1, response.getStatusCode());
  }

  @Then("response should contain recipes")
  public void response_should_contain_recipes() {
    assertThat(response.jsonPath()
                .getList("recipes"))
                .isNotEmpty();
  }

  @Then("number of recipes should be {int}")
  public void number_of_recipes_should_be(Integer expectedNumber) {
    int actualNumber = response.jsonPath()
                .getList("recipes")
                .size();

        assertThat(actualNumber)
                .isEqualTo(expectedNumber);
  }
}
