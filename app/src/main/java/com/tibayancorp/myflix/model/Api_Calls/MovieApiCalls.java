package com.tibayancorp.myflix.model.Api_Calls;

import android.support.annotation.NonNull;
import android.util.Log;

import com.tibayancorp.myflix.model.Entities.Movie;
import com.tibayancorp.myflix.model.Entities.MovieListResponse;
import com.tibayancorp.myflix.model.Interface.MovieApiService;
import com.tibayancorp.myflix.model.RetrofitFactory;
import com.tibayancorp.myflix.utilities.API;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.tibayancorp.myflix.utilities.API.KEY;

/**
 * Created by JanMichael on 13/10/2018.
 */

public class MovieApiCalls {
    private List<Movie> movies;
    private boolean success = false;
    private int orderOfCall;
    /******* Movie List Filter Options Implementation *******/

    public void callTopRatedMoviesAPI(){
        MovieApiService movieApiService = RetrofitFactory.callMovieInterface();
        Call<MovieListResponse> call = movieApiService.getTopRatedMovies(KEY);
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

    public void callPopularMoviesAPI(){
        MovieApiService movieApiService = RetrofitFactory.callMovieInterface();
        Map<String, String> params = new HashMap<>();
        params.put("api_key",API.KEY);
        params.put("sort_by","popularity.desc");
        Call<MovieListResponse> call = movieApiService.getPopularMovies(params);
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

    /* For Multiple Genre Selection in a submenu, this might be more complex.
    I’ll restrict the filtering to one genre at a time, for this reason and for simplicity.
    Since when multiple genres are selected, the resulting list will be mixed and might not be what the user will want.

    Due to above reason, the below requirements may be reconsidered for future release/expansion

        The Genre Set passed must be in this format: "21,22,121"
        Every selection of the user in Genre submenu must be appended and handled by the ViewModel calling this method

    */

    public void callGenreAPI(String genre){
        if (genre == null){
            Log.e(TAG, "The genre received in API Call was null");
        } else if (genre.equals("")){
            Log.e(TAG, "No genre was received in API Call");
        }
        else {
            MovieApiService movieApiService = RetrofitFactory.callMovieInterface();
            Map<String, String> params = new HashMap<>();
            params.put("api_key", API.KEY);
            params.put("with_genres", genre);
            Call<MovieListResponse> call = movieApiService.getMoviesFromGenre(params);
            call.enqueue(new Callback<MovieListResponse>() {
                @Override
                public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            List<Movie> movies = response.body().getResults();
                            setMovieListResult(movies);
                            Log.d(TAG, "Number of movies received: " + movies.size());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MovieListResponse> call, Throwable throwable) {
                    Log.e(TAG, throwable.toString());
                }
            });
        }
    }

    /** Will need to call this twice in ViewModel and merge both US and Philippines results
     * regionValue = "PH" or "US" */

    public void callNowShowingMoviesAPI(String regionValue, final int orderOfCall){
        MovieApiService movieApiService = RetrofitFactory.callMovieInterface();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", API.KEY);
        params.put("region", regionValue);
        Call<MovieListResponse> call = movieApiService.getNowShowingMovies(params);
        call.enqueue(new Callback<MovieListResponse>(){
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                try {
                    Log.d(TAG,"Request: " + call.request().toString());
                    if (response.isSuccessful()) {
                        if(getMovieListResult() == null && orderOfCall == 1) {
                            List<Movie> movies = response.body().getResults();
                            setMovieListResult(movies);
                            setSuccessful(true, orderOfCall);
                            Log.d(TAG, "Number of movies received: " + movies.size());
                        }

                        if(getMovieListResult() != null && getMovieListResult().size() > 0 && orderOfCall == 2){
                            List<Movie> movies = response.body().getResults();
                            getMovieListResult().addAll(movies);
                            setSuccessful(true, orderOfCall);
                            Log.d(TAG, "Number of movies received: " + movies.size());
                            Log.d(TAG, "Number of movies total received: " + getMovieListResult().size());
                        } else {
                            setSuccessful(true, orderOfCall);
                            Log.e(TAG, "No movies were added to the list" + getMovieListResult().size());
                            Log.e(TAG, "Movie List size: " + getMovieListResult().size()
                                    + "\nMovies from Response size: " + movies.size());

                        }
                    } else {
                        setSuccessful(false, orderOfCall);
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

    /** Will need to call this twice in ViewModel and merge both US and Philippines results
     * regionValue = "PH" or "US" */

    public void callUpcomingMoviesAPI(String regionValue, final int orderOfCall){
        MovieApiService movieApiService = RetrofitFactory.callMovieInterface();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", API.KEY);
        params.put("region", regionValue);
        Call<MovieListResponse> call = movieApiService.getUpcomingMovies(params);
        Callback<MovieListResponse> callback = new Callback<MovieListResponse>(){
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if(getMovieListResult() == null && orderOfCall == 1) {
                            List<Movie> movies = response.body().getResults();
                            setMovieListResult(movies);
                            setSuccessful(true, orderOfCall);
                            Log.d(TAG, "Number of movies received: " + movies.size());
                        }

                        if(getMovieListResult() != null && getMovieListResult().size() > 0 && orderOfCall == 2){
                            List<Movie> movies = response.body().getResults();
                            getMovieListResult().addAll(movies);
                            setSuccessful(true, orderOfCall);
                            Log.d(TAG, "Number of movies received: " + movies.size());
                            Log.d(TAG, "Number of movies total received: " + getMovieListResult().size());
                        } else {
                            setSuccessful(true, orderOfCall);
                            Log.e(TAG, "No movies were added to the list" + getMovieListResult().size());
                            Log.e(TAG, "Movie List size: " + getMovieListResult().size()
                                    + "\nMovies from Response size: " + movies.size());
                        }
                    } else {
                        setSuccessful(false, orderOfCall);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        };
        call.enqueue(callback);
    }



    public void setMovieListResult(List<Movie> movies){
        this.movies = movies;
    }

    public List<Movie> getMovieListResult(){
            return movies;
    }

    public boolean isSuccessful() {
        return success;
    }

    public int getOrderOfCall(){
        return orderOfCall;
    }

    public void setSuccessful(boolean success, int orderOfCall) {
        this.success = success;
        this.orderOfCall = orderOfCall;
    }
}
