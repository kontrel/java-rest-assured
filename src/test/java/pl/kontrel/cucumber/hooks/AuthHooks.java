package pl.kontrel.cucumber.hooks;

import io.cucumber.java.Before;
import pl.kontrel.api.AuthApi;
import pl.kontrel.context.TestContext;
import pl.kontrel.models.auth.Auth;
import pl.kontrel.models.auth.LoginRequest;

public class AuthHooks {

  private final TestContext context;
  private final AuthApi authApi;

  public AuthHooks(TestContext context) {
    this.context = context;
    this.authApi = new AuthApi();
  }

  @Before("@authenticated")
  public void authenticate() {
    LoginRequest loginBody = new LoginRequest("emilys", "emilyspass", 30);

    Auth auth = authApi.getAuthResponse(loginBody);

    context.setAccessToken(auth.getAccessToken());
    context.setUserId(auth.getId());
  }
}
