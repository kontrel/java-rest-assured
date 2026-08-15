package pl.kontrel.specifications;

import pl.kontrel.config.Configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class RequestSpecificationFactory {
    
    public static RequestSpecification defaultSpec() {
        return new RequestSpecBuilder()
        .setBaseUri(Configuration.getUrl())
        .setContentType(ContentType.JSON)
        .build();
    }
}
