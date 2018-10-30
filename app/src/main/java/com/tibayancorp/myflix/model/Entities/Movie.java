package com.tibayancorp.myflix.model.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JanMichael on 23/09/2018.
 */

public class Movie {
    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;
    
    @SerializedName("vote_average")
    private String vote_average;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private String id;

   // @SerializedName("imdb_id")
    //private String imdbId;

    @SerializedName("release_date")
    private String release_date;

    public Movie(String id, String title, String posterPath, String backdropPath, String overview, String release_date, String vote_average) {
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.title = title;
        this.id = id;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }



    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //public String getImdbId() {
      //  return imdbId;
   // }

   // public void setImdbId(String imdbId) {
       // this.imdbId = imdbId;
   // }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRelease_date() {
        return release_date;
    }
    
    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getVote_average() {
        return vote_average;
    }
}
