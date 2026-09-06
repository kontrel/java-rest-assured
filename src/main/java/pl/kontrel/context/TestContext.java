package pl.kontrel.context;

import io.restassured.response.Response;
import pl.kontrel.models.Recipe;

public class TestContext {

    private Recipe recipe;
    private Response response;

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
}
