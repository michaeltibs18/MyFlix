package com.tibayancorp.myflix.model.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JanMichael on 23/09/2018.
 */

public class MovieListResponse {

    /** To further understand this annotations, test it out, with @Expose and without.
     * @Expose is useful for explicitly specifying which fields needs to be serialized or deserialized.
     *
     * Test as well if there would still be a response if one these fuckers doesn't have @SerializedName.
     *
     * Use "results" for example, since this is the field used directly by the response.
     *
     * What error will occur. */

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("results")
    @Expose
    private List<Movie> results;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;


    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public List<Movie> getResults() {
        return results;
    }
    public void setResults(List<Movie> results) {
        this.results = results;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
