package com.tibayancorp.myflix.model.Interface;

import com.tibayancorp.myflix.model.Entities.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JanMichael on 23/09/2018.
 */

public interface MovieApiService {
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies (@Query("api_key") String apii_key);
}
