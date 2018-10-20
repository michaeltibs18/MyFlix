package com.tibayancorp.myflix.model.Interface;

import com.tibayancorp.myflix.model.Entities.MovieListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by JanMichael on 23/09/2018.
 */

public interface MovieApiService {

    /** To understand @Query:
     *
     *  Simple Example:
     * <pre><code>
     * &#64;GET("/list")
     * Call&lt;ResponseBody&gt; list(@Query("page") int page);
     * </code></pre>
     * Calling with {@code foo.list(1)} yields {@code /list?page=1}.
     * <p>
     * Example with {@code null}:
     * <pre><code>
     * &#64;GET("/list")
     * Call&lt;ResponseBody&gt; list(@Query("category") String category);
     * </code></pre>
     * Calling with {@code foo.list(null)} yields {@code /list}.
     * <p>
     * Array/Varargs Example:
     * <pre><code>
     * &#64;GET("/list")
     * Call&lt;ResponseBody&gt; list(@Query("category") String... categories);
     * </code></pre>
     * Calling with {@code foo.list("bar", "baz")} yields
     * {@code /list?category=bar&category=baz}.
     * <p>
     */

    /* Right now, the url to get the filter options is like this:
    * https://api.themoviedb.org/3/discover/movie?api_key=<<api_key>>&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1*/

    /* Example for Top Rated Movies list: https://api.themoviedb.org/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1 */

    /* DIFFERENCE OF @PATH AND @QUERY IN SIMPLE TERMS:
        @PATH
            &#64;GET("/image/{id}")
            Call&lt;ResponseBody&gt; example(@Path("id") int id);
            </code></pre>
            Calling with {@code foo.example(1)} yields {@code /image/1}.

        @QUERY
            &#64;GET("/list")
            Call&lt;ResponseBody&gt; list(@Query("page") int page);
            </code></pre>
            Calling with {@code foo.list(1)} yields {@code /list?page=1}.
     */

    @GET("movie/top_rated")
    Call<MovieListResponse> getTopRatedMovies (@Query("api_key") String api_key);

    /** Usage of @QUERYMAP
         &#64;GET("/search")
         Call&lt;ResponseBody&gt; list(@QueryMap Map&lt;String, String&gt; filters);
         </code></pre>
         Calling with {@code foo.list(ImmutableMap.of("foo", "bar", "kit", "kat"))} yields
         {@code /search?foo=bar&kit=kat}.
     */

    /** For below api call, we want 2 Mappings:
     * Key = "api_key" and Value = API.Key
     * Key = "sort_by" and Value = "popularity.desc" */

    @GET("discover/movie")
    Call<MovieListResponse> getPopularMovies (@QueryMap Map<String,String> filters);

    /** For below api call, we want 2 Mappings:
     * Key = "api_key" and Value = API.Key
     * Key = "with_genres" and Value = <Comma separated value of genre ids that you want to include in the results>
     *
     * 10/20/18 Update:
     * For Multiple Genre Selection in a submenu, this might be more complex.
     I’ll restrict the filtering to one genre at a time, for this reason and for simplicity.
     Since when multiple genres are selected, the resulting list will be mixed and might not be what the user will want.

     Due to above reason, the below requirements may be reconsidered for future release/expansion
        Every selected genre will append their genre_id to the Value of Key “with_genres” with commas
     */

    @GET("discover/movie")
    Call<MovieListResponse> getMoviesFromGenre (@QueryMap Map<String,String> filters);

    /** For below api call, we want 2 Mappings:
     * Key = "api_key" and Value = API.Key
     * Key = "region" and Value = PH or US for more movies
     *
     *  I need to do 2 requests. And merge both results for PH and US Regions and use their Release dates as a sort by.
     *  So it won't look so separated. Or if that's too time consuming, try first to only merge the results in the RecyclerView.
     *
     * Because if only PH, it will only show Filipino Films not necessarily all films actually showing in theatres,
     * which includes US Films, such as Venom.
     */

    @GET("movie/upcoming")
    Call<MovieListResponse> getUpcomingMovies (@QueryMap Map<String,String> filters);

    /** For below api call, we want 2 Mappings:
     * Key = "api_key" and Value = API.Key
     * Key = "region" and Value = PH or US for more movies
     *
     * I need to do 2 requests. And merge both results for PH and US Regions and use their Release dates as a sort by.
     *  So it won't look so separated. Or if that's too time consuming, try first to only merge the results in the RecyclerView.
     *
     * Because if only PH, it will only show Filipino Films not necessarily all films actually showing in theatres,
     * which includes US Films, such as Venom.
     */
    @GET("movie/now_playing")
    Call<MovieListResponse> getNowShowingMovies (@QueryMap Map<String,String> filters);

}
