package com.tibayancorp.myflix.view_model;

import com.tibayancorp.myflix.model.Api_Calls.MovieApiCalls;

/**
 * Created by JanMichael on 12/10/2018.
 */

public class MovieListViewModel {
    MovieApiCalls movieApiCalls;


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
                movieApiCalls.callUpcomingMoviesAPI("PH");
                // not sure yet how to merge it but
                // movieApiCalls.callUpcomingMoviesAPI("US");
                break;
            case "Now Showing":
                movieApiCalls.callNowShowingMoviesAPI("PH");
                // movieApiCalls.callUpcomingMoviesAPI("US");
                break;
        }
    }

}
