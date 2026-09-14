package pl.kontrel.api;

import static io.restassured.RestAssured.given;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;

import io.restassured.response.Response;
import pl.kontrel.models.Recipe;

public class RecipesApi {

  public Response getRecipes() {
    return given()
      .spec(defaultSpec())
      .when().get("/recipes")
      .then()
      .extract().response();
  }

  public Response getRecipeByName(String recipeName) {
    return given()
      .spec(defaultSpec())
      .when().get("/recipes/search?q={name}", recipeName)
      .then()
      .extract().response();
  }

  public Response getRecipesWithLimit(Integer limit) {
    return given()
      .spec(defaultSpec())
      .when().get("/recipes?limit={limit}", limit)
      .then()
      .extract().response();
  }

  public Response addRecipe(Recipe recipe) {
    return given()
      .spec(defaultSpec())
      .body(recipe)
      .when().post("/recipes/add")
      .then()
      .extract().response();
  }

  public Response deleteRecipe(int recipeId) {
    return given()
      .spec(defaultSpec())
      .pathParam("id", recipeId)
      .when().delete("/recipes/{id}")
      .then()
      .extract().response();
  }
}
