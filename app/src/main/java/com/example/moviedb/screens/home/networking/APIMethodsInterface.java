package com.example.moviedb.screens.home.networking;

import com.example.moviedb.screens.home.model.GenreJSONResults;
import com.example.moviedb.screens.home.model.MovieJSONResult;
import com.example.moviedb.screens.home.model.MovieDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIMethodsInterface {

    @GET("discover/movie")
    Call<MovieJSONResult> getMovies(@Query("api_key") String key, @Query("with_genres") String geners, @Query("sort_by") String popularity, @Query("page") String page);

    @GET("genre/movie/list")
    Call<GenreJSONResults> getGenre(@Query("api_key") String key);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String movieID, @Query("api_key") String key);



}
