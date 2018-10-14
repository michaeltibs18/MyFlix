package com.tibayancorp.myflix.model.Api_Calls;

import android.util.Log;

import com.tibayancorp.myflix.model.Entities.Movie;
import com.tibayancorp.myflix.model.Entities.MovieListResponse;
import com.tibayancorp.myflix.model.Interface.MovieApiService;
import com.tibayancorp.myflix.model.RetrofitFactory;
import com.tibayancorp.myflix.utilities.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by JanMichael on 13/10/2018.
 */

public class MovieApiCalls {
    private List<Movie> movies;


    public void callTopRatedMoviesAPI(){
        MovieApiService movieApiService = RetrofitFactory.callMovieInterface();
        Call<MovieListResponse> call = movieApiService.getTopRatedMovies(API.KEY);
        call.enqueue(new Callback<MovieListResponse>(){
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Movie> movies = response.body().getResults();
                        setMovieListResult(movies);
                        Log.d(TAG, "Number of movies received: " + movies.size());
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

    public void setMovieListResult(List<Movie> movies){
        this.movies = movies;
    }

    public List<Movie> getMovieListResult(){
            return movies;
    }
}
