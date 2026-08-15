package pl.kontrel;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static pl.kontrel.specifications.RequestSpecificationFactory.defaultSpec;

public class FirstTest {

    @Test
    public void test() {
        
        given()
        .log().all()
        .spec(defaultSpec())
        .when().get("/ping")
        .then()
        .log().all()
        .statusCode(201);

    
    }

    @Test
    public void getAllBookings() {
        given()
        .log().all()
        .spec(defaultSpec())
        .when().get("/booking")
        .then().log().all();
    }
}
