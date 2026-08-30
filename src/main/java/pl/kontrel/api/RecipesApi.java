package pl.kontrel.api;

import static io.restassured.RestAssured.given;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;
import io.restassured.response.Response;

public class RecipesApi {
  
  public Response getRecipes() {
    return given()
        .spec(defaultSpec()).log().all()
        .when().get("/recipes")
        .then().log().all()
        .extract().response();
  }
}
