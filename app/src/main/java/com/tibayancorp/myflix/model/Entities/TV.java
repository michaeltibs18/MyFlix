package com.tibayancorp.myflix.model.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JanMichael on 23/09/2018.
 */

public class TV {
    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("name")
    private String name;

    @SerializedName("number_of_seasons")
    private String noOfSeasons;

    @SerializedName("number_of_episodes")
    private String noOfEpisodes;


    public TV(String id, String name,  String overview, String noOfSeasons, String noOfEpisodes, String type, String posterPath, String backdropPath) {
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.type = type;
        this.id = id;
        this.name = name;
        this.noOfSeasons = noOfSeasons;
        this.noOfEpisodes = noOfEpisodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoOfSeasons() {
        return noOfSeasons;
    }

    public void setNoOfSeasons(String noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }

    public String getNoOfEpisodes() {
        return noOfEpisodes;
    }

    public void setNoOfEpisodes(String noOfEpisodes) {
        this.noOfEpisodes = noOfEpisodes;
    }
}
