package com.spotify.tests;

import com.spotify.pojo.Playlist;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    String access_token = "BQC-e2T4YHzUgnbtAJJq1fysB_SWNNE7iXW-Y_QC-47MF0a3mIN0YXSaD8LDaiiofJxonvHg8fZWiIKB2mqFF3O2pr863GrU9H7FtiBKDLExFvEdJadyayOwmxYEtGTmGddasm1saOQDApEfC6uTfXOcbmk6X3fs3SaaXkGM2ZR4f7l8Qu8IdpAb3vMqpd2EHv2gz24HnfYqThqrQWk5iJ6gfAokmc-rbFw_sh4AsbAG6sae-EmyWWVRUzaX-J2pzKSnn35MHQAEKEEF";
    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.spotify.com").
                addHeader("Authorization","Bearer "+access_token)
//                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
       responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void shouldBeAbleToCreatePlaylist(){


        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New Songs1");
        requestPlaylist.setDescription("This is a New Song1 playlist");
        requestPlaylist.setPublic(true);

       Playlist responsePlaylist =  given(requestSpecification).
                body(requestPlaylist)
                .when()
                .post("/v1/users/31hzu4nvpkvxlfvae57pzfam2spi/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .extract()
                .response()
                .as(Playlist.class);

       assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }


    @Test
    public void shouldBeAbleToGetPlaylist(){

        given(requestSpecification)
                .when()
                .get("/v1/playlists/4ewMWd63jIrDLj92wUfIgp")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("My New Playlist"), "description", equalTo("This is a updated description"), "public", equalTo(true));

    }

    @Test
    public void shouldBeAbleToUpdatePlaylist(){
        String payload = "{\n" +
                "    \"name\": \"My New Updated Playlist1\",\n" +
                "    \"description\": \"This playlist1 is updated\",\n" +
                "    \"public\": true\n" +
                "}";
        given(requestSpecification).
                body(payload)
                .when()
                .put("/v1/playlists/3QAzxic42YZXKlWpoDYbpI")
                .then()
//                .spec(responseSpecification)
                .assertThat()
                .statusCode(200);

    }


}
