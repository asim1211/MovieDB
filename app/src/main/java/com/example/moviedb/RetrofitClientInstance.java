package com.example.moviedb;

import com.example.moviedb.screens.home.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance implements MyInterface{
    private static RetrofitClientInstance instance;
    private MyInterface myInterface;

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private RetrofitClientInstance(){
        this.myInterface = getRetrofitInstance().create(MyInterface.class);
    }

    public Retrofit getRetrofitInstance() {
                return new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }

    public void getMovies(String genre, Callback<JSONResult> resultCallback) {
        myInterface.getMovies(MainActivity.key, genre, "popularity.desc").enqueue(resultCallback);
    }

    public void getGenres(Callback<GenreJSONResults> resultCallback) {
        myInterface.getGenre(MainActivity.key).enqueue(resultCallback);
    }

    public static RetrofitClientInstance getInstance() {
        if (instance == null)
            instance = new RetrofitClientInstance();
        return instance;
    }

    @Override
    public Call<JSONResult> getMovies(String key, String geners, String popularity) {
        return myInterface.getMovies(key,geners,popularity);
    }

    @Override
    public Call<GenreJSONResults> getGenre(String key) {
        return myInterface.getGenre(key);
    }
}
