package com.yucatancorp.petagram.restAPI;

import com.yucatancorp.petagram.restAPI.model.mascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marcos on 20/04/2017.
 */

public interface EndPointApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<mascotaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_SEARCH_USERS)
    Call<mascotaResponse> search(@Query("q") String query, @Query("access_token") String accessToken);

}
