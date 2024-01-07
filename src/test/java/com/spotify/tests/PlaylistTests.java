package com.spotify.tests;

import com.spotify.api.applicationApi.PlaylistApi;
import com.spotify.pojo.Error;
import com.spotify.pojo.Playlist;
import com.spotify.utils.DataLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.api.SpecBuilder.getRequestSpec;
import static com.spotify.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

@Story("Create a Playlist Story")
@Description("With this POST API user should be able to create a new Playlist")
    @Test(description = "User should be abe to create new playlist")
    public void shouldBeAbleToCreatePlaylist(){


        Playlist requestPlaylist = new Playlist()
                .setName("New Songs1")
                .setDescription("This is a New Song1 playlist")
                .setPublic(true);

//        requestPlaylist.setName("New Songs1");
//        requestPlaylist.setDescription("This is a New Song1 playlist");
//        requestPlaylist.setPublic(true);

        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(201));

        Playlist responsePlaylist = response.as(Playlist.class);

       assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }


    @Test
    public void shouldBeAbleToGetPlaylist(){

      Playlist requestPlaylist = new Playlist()
            .setName("My New Playlist")
            .setDescription("This is a updated description")
            .setPublic(true);

      Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());

      System.out.println(response.asString());

      assertThat(response.statusCode(), equalTo(200));

      Playlist responsePlaylist = response.as(Playlist.class);


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

        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertThat(response.statusCode(), equalTo(200));

    }


    @Test
    public void shouldNotBeAbleToCreatePlaylist(){


        Playlist requestPlaylist = new Playlist();
//        requestPlaylist.setName("New Songs1");
        requestPlaylist.setDescription("This is a New Song1 playlist");
        requestPlaylist.setPublic(true);

        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(400));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

    }


    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken(){

        String invalidToken = "123ABC123abc";


        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New Songs1");
        requestPlaylist.setDescription("This is a New Song1 playlist");
        requestPlaylist.setPublic(true);

        Response response = PlaylistApi.post(requestPlaylist, invalidToken);
        assertThat(response.statusCode(), equalTo(401));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));

    }


}
