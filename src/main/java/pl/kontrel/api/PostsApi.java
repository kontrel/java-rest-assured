package pl.kontrel.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;

public class PostsApi {

  public Response getAllPosts() {
    return given()
      .spec(defaultSpec())
      .when()
      .get("/posts")
      .thenReturn();
  }

  public Response getPostWithSearchWord(String word) {
    return given()
      .spec(defaultSpec())
      .queryParam("q", word)
      .when()
      .get("/posts/search")
      .thenReturn();
  }

  public Response getCurrentUserPosts(int userId) {
    return given()
      .spec(defaultSpec())
      .pathParam("userId", userId)
      .when()
      .get("/posts/user/{userId}")
      .thenReturn();
  }
}
