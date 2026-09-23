package pl.kontrel.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static pl.kontrel.config.Configuration.getUrl;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;

public class UsersApi {

  public Response getCurrentUser(String token) {
    return given()
      .baseUri(getUrl())
      .header("Authorization", "Bearer " + token)
      .when()
      .get("/auth/me")
      .thenReturn();
  }

  public Response getAllUsers() {
    return given()
      .spec(defaultSpec())
      .when()
      .get("/users")
      .thenReturn();
  }

  public Response getAllUsersWithParams(int limit, String select) {
    return given()
      .spec(defaultSpec())
      .queryParam("limit", limit)
      .queryParam("select", select)
      .when()
      .get("/users")
      .thenReturn();
  }
}
