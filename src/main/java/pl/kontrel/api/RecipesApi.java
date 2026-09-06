package pl.kontrel.api;

import static io.restassured.RestAssured.given;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;

import io.restassured.response.Response;
import pl.kontrel.models.Recipe;

public class RecipesApi {

  public Response getRecipes() {
    return given()
      .spec(defaultSpec()).log().all()
      .when().get("/recipes")
      .then().log().all()
      .extract().response();
  }

  public Response getRecipeByName(String recipeName) {
    return given()
      .spec(defaultSpec()).log().all()
      .when().get("/recipes/search?q={name}", recipeName)
      .then().log().all()
      .extract().response();
  }

  public Response getRecipesWithLimit(Integer limit) {
    return given()
      .spec(defaultSpec()).log().all()
      .when().get("/recipes?limit={limit}", limit)
      .then().log().all()
      .extract().response();
  }

  public Response addRecipe(Recipe recipe) {
    return given()
      .spec(defaultSpec()).log().all()
      .body(recipe)
      .when().post("/recipes/add")
      .then().log().all()
      .extract().response();
  }

  public Response deleteRecipe(int recipeId) {
    return given()
      .spec(defaultSpec()).log().all()
      .pathParam("id", recipeId)
      .when().delete("/recipes/{id}")
      .then().log().all()
      .extract().response();
  }
}
