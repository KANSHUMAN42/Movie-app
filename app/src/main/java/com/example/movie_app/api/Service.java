package com.example.movie_app.api;

import com.example.movie_app.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/popular")
    Call<MovieResponse>getpopularMovies(@Query("api_key") String apikey);

}
