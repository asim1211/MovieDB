package com.example.moviedb.screens.home.networking;

import com.example.moviedb.screens.home.model.GenreJSONResults;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.model.MovieDeserializer;
import com.example.moviedb.screens.home.model.MovieJSONResult;
import com.example.moviedb.screens.home.model.MovieDetails;
import com.example.moviedb.screens.home.screens.movies.view.MainActivity;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance implements APIMethodsInterface {
    private static RetrofitClientInstance instance;
    private APIMethodsInterface apiMethodsInterface;

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private RetrofitClientInstance(){
        this.apiMethodsInterface = getRetrofitInstance().create(APIMethodsInterface.class);
    }

    private Retrofit getRetrofitInstance() {
                return new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(Movie.class , new MovieDeserializer()).create()))
                        .build();
    }

    public void getMovies(String genre, String page, Callback<MovieJSONResult> resultCallback) {
        apiMethodsInterface.getMovies(MainActivity.key, genre, "popularity.desc", page).enqueue(resultCallback);
    }

    public void getGenres(Callback<GenreJSONResults> resultCallback) {
        apiMethodsInterface.getGenre(MainActivity.key).enqueue(resultCallback);
    }

    public void getMovieDetails(String id,Callback<MovieDetails> resultCallback){

        apiMethodsInterface.getMovieDetails(id,MainActivity.key).enqueue(resultCallback);
    }

    public static RetrofitClientInstance getInstance() {
        if (instance == null)
            instance = new RetrofitClientInstance();
        return instance;
    }

    @Override
    public Call<MovieJSONResult> getMovies(String key, String geners, String popularity, String page) {
        return apiMethodsInterface.getMovies(key, geners, popularity, page);
    }

    @Override
    public Call<GenreJSONResults> getGenre(String key) {
        return apiMethodsInterface.getGenre(key);
    }

    @Override
    public Call<MovieDetails> getMovieDetails(String key,String movieID) { return apiMethodsInterface.getMovieDetails(key, movieID); }
}
