package com.spotify.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.api.Routes.BASE_PATH;

public class SpecBuilder {
//    static String access_token = "BQAFTyYFbepUDWxmMqBWT9Q38gzZ416yZQjQr--ZmmhQH1HQwF6eUXdUKmcbStjUvwiFTzZP2XSVwP1Ew2xzzIkciSEb7LzDmCxNYTf9qQZgLYKHAyD9jYW93NurvLzpfvlqbRVOK4S2AK6HxJfzs4hPTiKTbjXTH_nxSWrsTu6HOZnb-OTliVQX5sei2qgvYYU580_LlkyBV8ih5NW-oKlp65W7ldHk8omjV4euw9SwD7GHdvrG4SIKmpvFXTA5poLhG886K9FaZDMK";


    public static RequestSpecification getRequestSpec() {

    return new RequestSpecBuilder().setBaseUri("https://api.spotify.com").
            setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();
}

    public static RequestSpecification getAccountRequestSpec() {

        return new RequestSpecBuilder().setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL).build();
    }

public static ResponseSpecification getResponseSpec(){

    return new ResponseSpecBuilder()
//            .expectStatusCode(201).
//            expectContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();
}


}
