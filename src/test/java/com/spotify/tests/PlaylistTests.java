package com.spotify.tests;

import com.spotify.pojo.Error;
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

import static com.spotify.api.SpecBuilder.getRequestSpec;
import static com.spotify.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {



    @Test
    public void shouldBeAbleToCreatePlaylist(){


        Playlist requestPlaylist = new Playlist()
                .setName("New Songs1")
                .setDescription("This is a New Song1 playlist")
                .setPublic(true);

//        requestPlaylist.setName("New Songs1");
//        requestPlaylist.setDescription("This is a New Song1 playlist");
//        requestPlaylist.setPublic(true);

       Playlist responsePlaylist =  given(getRequestSpec()).
                body(requestPlaylist)
                .when()
                .post("/v1/users/31hzu4nvpkvxlfvae57pzfam2spi/playlists")
                .then()
                .spec(getResponseSpec())
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

      Playlist requestPlaylist = new Playlist();
      requestPlaylist.setName("My New Playlist");
      requestPlaylist.setDescription("This is a updated description");
      requestPlaylist.setPublic(true);

       Playlist responsePlaylist =  given(getRequestSpec())
                .when()
                .get("/v1/playlists/4ewMWd63jIrDLj92wUfIgp")
                .then()
                .spec(getResponseSpec())
                .assertThat()
                .statusCode(200)
               .extract()
               .response()
               .as(Playlist.class);

       assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
       assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
       assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));

    }

    @Test
    public void shouldBeAbleToUpdatePlaylist(){


        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("My New Updated Playlist1");
        requestPlaylist.setDescription("This playlist1 is updated");
        requestPlaylist.setPublic(true);


       given(getRequestSpec()).
                body(requestPlaylist)
                .when()
                .put("/v1/playlists/3QAzxic42YZXKlWpoDYbpI")
                .then()
                .spec(getResponseSpec())
                .assertThat()
                .statusCode(200);

    }


    @Test
    public void shouldNotBeAbleToCreatePlaylist(){


        Playlist requestPlaylist = new Playlist();
//        requestPlaylist.setName("New Songs1");
        requestPlaylist.setDescription("This is a New Song1 playlist");
        requestPlaylist.setPublic(true);

        Error error =  given(getRequestSpec()).
                body(requestPlaylist)
                .when()
                .post("/v1/users/31hzu4nvpkvxlfvae57pzfam2spi/playlists")
                .then()
                .spec(getResponseSpec())
                .assertThat()
                .statusCode(400)
                .extract()
                .response()
                .as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

    }


    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken(){


        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New Songs1");
        requestPlaylist.setDescription("This is a New Song1 playlist");
        requestPlaylist.setPublic(true);

        Error error =  given(getRequestSpec()).
                body(requestPlaylist)
                .when()
                .post("/v1/users/31hzu4nvpkvxlfvae57pzfam2spi/playlists")
                .then()
                .spec(getResponseSpec())
                .assertThat()
                .statusCode(401)
                .extract()
                .response()
                .as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));

    }


}
