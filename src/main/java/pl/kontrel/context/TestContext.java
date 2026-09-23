package pl.kontrel.context;

import io.restassured.response.Response;
import pl.kontrel.models.recipes.Recipe;

public class TestContext {

    private Recipe recipe;
    private Response response;
    private String accessToken;
    private int userId;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() { return accessToken; }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
