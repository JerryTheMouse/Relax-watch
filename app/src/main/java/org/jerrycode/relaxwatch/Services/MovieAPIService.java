package org.jerrycode.relaxwatch.Services;

import android.support.annotation.NonNull;

import org.jerrycode.relaxwatch.Models.MovieAPIResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jerry on 12/18/15.
 */
public interface MovieAPIService {
    @GET("/3/discover/movie/")
    Call<MovieAPIResponse> listMovies(@NonNull @Query("sort_by") String sortBy);
}
