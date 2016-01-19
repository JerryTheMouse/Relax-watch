package org.jerrycode.relaxwatch.Services;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;

import org.jerrycode.relaxwatch.Models.MovieAPIResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jerry on 12/18/15.
 */
public interface MovieAPIService {
    @GET("/3/discover/movie/")
    Call<MovieAPIResponse> listMovies(@NonNull @Query("sort_by") String sortBy);

    @GET("/3/movie/{id}/videos")
    Call<JsonElement> getTrailers(@Path("id") int id);

    @GET("/3/movie/{id}/reviews")
    Call<JsonElement> getReviews(@Path("id") int id);
}
