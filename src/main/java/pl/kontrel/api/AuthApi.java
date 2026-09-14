package pl.kontrel.api;

import io.restassured.response.Response;
import pl.kontrel.models.auth.Auth;
import pl.kontrel.models.auth.LoginRequest;

import static io.restassured.RestAssured.given;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;
import static pl.kontrel.config.Configuration.getUrl;

public class AuthApi {

  public Auth getAuthResponse(LoginRequest loginBody) {
    return given()
      .spec(defaultSpec())
      .body(loginBody)
      .when()
      .post("/auth/login")
      .then()
      .extract().response().as(Auth.class);
  }

  public Response getCurrentUser(String token) {
    return given()
      .baseUri(getUrl())
      .header("Authorization", "Bearer " + token)
      .when()
      .get("/auth/me")
      .then()
      .extract().response();
  }
}
