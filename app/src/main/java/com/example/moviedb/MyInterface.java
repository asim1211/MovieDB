package com.example.moviedb;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MyInterface {

    @GET("discover/movie")
    Call<JSONResult> getMovies(@Query("api_key") String key,@Query("with_genres") int geners,@Query("sort_by") String popularity);

    @GET("genre/movie/list")
    Call<GenreJSONResults> getGenre(@Query("api_key") String key);



}