package com.spotify.api.applicationApi;

import com.spotify.api.RestResource;
import com.spotify.pojo.Playlist;
import com.spotify.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.api.Routes.PLAYLISTS;
import static com.spotify.api.Routes.USERS;
import static com.spotify.api.TokenManager.getToken;


public class PlaylistApi {

//    static String access_token = "BQAqQJ9ufNFjrJjs1T1vI2h44T77ZEhVO4RlXf_GvkygRUa7mCB00Hf-muX_Mng4jfJDAdN63K0SofTkySHeR7D1GKFGAZ9SEf92Hs6a_UHpZhkAl_L6J5PLpt2JSuD2Eh9QshV9KTyuMjHWhsBEzYaHkQuiOVhWgBS9eLs5WySHNh6FoNP5JGyIAvZFxPqV8pE_DPQdfDSOf--eAMVIftjckk7g6kKuRuyD4VON4tWEcIjmUkpKsUvCYlbDUIAVlkAyA1SeYu1DSddS";


    public static Response post(Playlist requestPlaylist){
        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId()+ PLAYLISTS, getToken(), requestPlaylist);

    }

    public static Response post(Playlist requestPlaylist, String token){
        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId()+ PLAYLISTS, token, requestPlaylist);

    }

    public static Response get(String playlistId){

        return RestResource.get(PLAYLISTS+"/"+ playlistId, getToken());

    }

    public static Response put(String playlistId, Playlist requestPlaylist){
        return RestResource.put(PLAYLISTS+"/"+ playlistId, getToken(), requestPlaylist);


    }
}
