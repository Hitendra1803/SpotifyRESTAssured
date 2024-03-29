package com.spotify.api;

import com.spotify.pojo.Playlist;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.api.Routes.API;
import static com.spotify.api.Routes.TOKEN;
import static com.spotify.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response post(String path, String token, Object requestPlaylist){
        return  given(getRequestSpec()).
                body(requestPlaylist)
                .auth().oauth2(token)
//                .header("Authorization", "Bearer " + token)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();

    }

    @Step
    public static Response get(String path, String token){
        return given(getRequestSpec())
                .auth().oauth2(token)
//                .header("Authorization", "Bearer " + token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    @Step
    public static Response put(String path, String token, Object requestPlaylist){
       return  given(getRequestSpec()).
                body(requestPlaylist)
               .auth().oauth2(token)
//               .header("Authorization", "Bearer " + token)
                .when()
                .put(path)
                .then()
                .spec(getResponseSpec())
               .extract()
               .response();

    }

    @Step
    public static Response postAccount(HashMap<String, String> formParams){
        return  given(getAccountRequestSpec())
                .formParams(formParams)
                .when().post(API+TOKEN)
                .then().spec(getResponseSpec())
                .extract().response();
    }
}
