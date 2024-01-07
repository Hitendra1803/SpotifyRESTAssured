package com.spotify.api;

import com.spotify.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){

        try {
            if(access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token ...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);

            }
            else {
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            throw new RuntimeException("ABORT!!! Failed to get Token");

        }
        return access_token;
    }
    private static Response renewToken(){
        HashMap<String, String> formParms = new HashMap<String, String>();

        formParms.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParms.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParms.put("client_id", ConfigLoader.getInstance().getClientId());
        formParms.put("client_secret", ConfigLoader.getInstance().getClientSecret());

        Response response = RestResource.postAccount(formParms);

        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!! Renew token Failed");
        }
        return response;
    }
}
