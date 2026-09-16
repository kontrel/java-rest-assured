package pl.kontrel.api;

import pl.kontrel.models.auth.Auth;
import pl.kontrel.models.auth.LoginRequest;

import static io.restassured.RestAssured.given;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;

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
}
