package pl.kontrel.specifications;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import pl.kontrel.config.Configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

import static java.util.Arrays.asList;

public class RequestSpecificationFactory {
    
    public static RequestSpecification defaultSpec() {
        return new RequestSpecBuilder()
        .setBaseUri(Configuration.getUrl())
        .setContentType(ContentType.JSON)
        .addFilters(asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
        .build();
    }
}
