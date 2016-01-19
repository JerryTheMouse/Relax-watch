package org.jerrycode.relaxwatch.Services;

import android.support.annotation.NonNull;

import org.jerrycode.relaxwatch.Models.Movie;
import org.jerrycode.relaxwatch.Models.MovieAPIResponse;
import org.jerrycode.relaxwatch.Models.Review;
import org.jerrycode.relaxwatch.Models.Trailer;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jerry on 12/18/15.
 */
public interface MovieAPIService {
    @GET("/3/discover/movie/")
    Call<MovieAPIResponse<Movie>> listMovies(@NonNull @Query("sort_by") String sortBy);

    @GET("/3/movie/{id}/videos")
    Call<MovieAPIResponse<Trailer>> getTrailers(@Path("id") int id);

    @GET("/3/movie/{id}/reviews")
    Call<MovieAPIResponse<Review>> getReviews(@Path("id") int id);
}
