package com.tibayancorp.myflix.view_model;

import android.util.Log;

import com.tibayancorp.myflix.model.Api_Calls.MovieApiCalls;
import com.tibayancorp.myflix.model.Entities.Movie;

import java.util.Collections;
import java.util.Comparator;
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
                if(movieApiCalls.isSuccessful() == true){
                    setMovieList(movieApiCalls.getMovieListResult());
                } else {
                    Log.e(TAG,"Top Rated Movies API Call was not successful");
                }
                break;
            case "Popularity":
                movieApiCalls.callPopularMoviesAPI();
                if(movieApiCalls.isSuccessful() == true){
                    setMovieList(movieApiCalls.getMovieListResult());
                } else {
                    Log.e(TAG,"Popular Movies API Call was not successful");
                }
                break;
            case "Action":
                movieApiCalls.callGenreAPI(genres[0]);
                if(movieApiCalls.isSuccessful() == true){
                    setMovieList(movieApiCalls.getMovieListResult());
                } else {
                    Log.e(TAG,"Action Genre API Call was not successful");
                }
                break;
            case "Romance":
                movieApiCalls.callGenreAPI(genres[1]);
                if(movieApiCalls.isSuccessful() == true){
                    setMovieList(movieApiCalls.getMovieListResult());
                } else {
                    Log.e(TAG,"Romance Genre API Call was not successful");
                }
                break;
            case "Comedy":
                movieApiCalls.callGenreAPI(genres[2]);
                if(movieApiCalls.isSuccessful() == true){
                    setMovieList(movieApiCalls.getMovieListResult());
                } else {
                    Log.e(TAG,"Comedy Genre API Call was not successful");
                }
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

    //Learn the solution here again: https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date#

    public void sortMovieListByReleaseDate(List<Movie> movies){
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie o1, Movie o2) {
                if (o1.getReleaseDate() == null || o2.getReleaseDate() == null)
                    return 0;
                return o1.getReleaseDate().compareTo(o2.getReleaseDate());
            }
        });
    }
    public void setMovieList(List<Movie> movies){
        this.movies = movies;
    }

    public List<Movie> getMovieList(){
        return movies;
    }

}
