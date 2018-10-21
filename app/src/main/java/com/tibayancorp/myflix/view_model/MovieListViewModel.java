package com.tibayancorp.myflix.view_model;

import android.util.Log;

import com.tibayancorp.myflix.model.Api_Calls.MovieApiCalls;
import com.tibayancorp.myflix.model.Entities.Movie;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by JanMichael on 12/10/2018.
 */

public class MovieListViewModel {
    MovieApiCalls movieApiCalls;
    List<Movie> movies;

    /*
    List all Genre Ids here used in TMDB API
    */
    String[] genres = {"21","22","33"};

    public void onSelectedFilter(String filter){
        switch (filter){
            case "Top Rated":
                movieApiCalls.callTopRatedMoviesAPI();
                break;
            case "Popularity":
                movieApiCalls.callPopularMoviesAPI();
                break;
            case "Action":
                movieApiCalls.callGenreAPI(genres[0]);
                break;
            case "Romance":
                movieApiCalls.callGenreAPI(genres[1]);
                break;
            case "Comedy":
                movieApiCalls.callGenreAPI(genres[2]);
                break;
            case "Upcoming":
                // must call it twice for PH and US Regions
                movieApiCalls.callUpcomingMoviesAPI("PH", 1);
                // not sure yet how to merge it but
                if(movieApiCalls.isSuccessful() == true && movieApiCalls.getOrderOfCall() == 1){
                    movieApiCalls.callUpcomingMoviesAPI("US", 2);
                } else {
                    Log.e(TAG,"1st Upcoming Movies API Call was not successful");
                }

                if(movieApiCalls.isSuccessful() == true && movieApiCalls.getOrderOfCall() == 2){
                    setMovieList(movieApiCalls.getMovieListResult());
                } else {
                    Log.e(TAG, "2nd Upcoming Movies API Call was not successful");
                }
                break;
            case "Now Showing":
                movieApiCalls.callNowShowingMoviesAPI("PH", 1);
                if(movieApiCalls.isSuccessful() == true && movieApiCalls.getOrderOfCall() == 1) {
                    movieApiCalls.callNowShowingMoviesAPI("US", 2);
                } else {
                    Log.e(TAG,"1st Now Showing Movies API Call was not successful");
                }

                if(movieApiCalls.isSuccessful() == true && movieApiCalls.getOrderOfCall() == 2) {
                    setMovieList(movieApiCalls.getMovieListResult());
                } else {
                    Log.e(TAG,"2nd Now Showing Movies API Call was not successful");
                }
                break;
        }
    }

    public void sortMovieListByReleaseDate(List<Movie> movies){
        if(movies != null && movies.size() > 0){
            for(int i = 0; i > movies.size(); i++){
                String date = movies.get(i).getRelease_date();
                
            }
        }
    }
    public void setMovieList(List<Movie> movies){
        this.movies = movies;
    }

    public List<Movie> getMovieList(){
        return movies;
    }

}
