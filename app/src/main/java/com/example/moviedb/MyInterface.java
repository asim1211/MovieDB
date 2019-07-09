package com.example.moviedb;

import com.example.moviedb.screens.home.networkingObjects.GenreJSONResults;
import com.example.moviedb.screens.home.networkingObjects.JSONResult;
import com.example.moviedb.screens.home.objects.MovieDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MyInterface {

    @GET("discover/movie")
    Call<JSONResult> getMovies(@Query("api_key") String key, @Query("with_genres") String geners, @Query("sort_by") String popularity);

    @GET("genre/movie/list")
    Call<GenreJSONResults> getGenre(@Query("api_key") String key);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String movieID, @Query("api_key") String key);



}
