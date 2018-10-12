package com.tibayancorp.myflix.model;

import com.tibayancorp.myflix.model.Interface.MovieApiService;
import com.tibayancorp.myflix.utilities.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JanMichael on 12/10/2018.
 */

public class RetrofitFactory {
    private static Retrofit retrofit = null;

    /* connectAndConnectAPIData() in https://android.jlelse.eu/consuming-rest-api-using-retrofit-library-in-android-ed47aef01ecb */

    /** Things to Note on Endpoints and BaseURL from the Retrofit documentation (comments)
     * The base url should always have a trailing slash ("/").
     * So the Endpoint values which are relative paths such as "movie/top_rated" will append themselves correctly.
     *
     * Excerpt from Retrofit.java
     * <b>Correct:</b><br>
     * Base URL: http://example.com/api/<br>
     * Endpoint: foo/bar/<br>
     * Result: http://example.com/api/foo/bar/
     *
     */
    public static Retrofit getRetrofitInstance(){
        try {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(API.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return  retrofit;
    }

    /** From https://github.com/Ginowine/android-retrofit/blob/master/app/src/main/java/app/movie/tutorial/com/rest/API.java */
    private static <T> T builder(Class<T> endpoint) {
        return new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint);
    }

    public static MovieApiService callMovieInterface(){
        return builder(MovieApiService.class);
    }

}
