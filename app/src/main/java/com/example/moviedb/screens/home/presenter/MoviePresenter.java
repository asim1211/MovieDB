package com.example.moviedb.screens.home.presenter;

import android.app.Activity;

import com.example.moviedb.Genre;
import com.example.moviedb.GenreJSONResults;
import com.example.moviedb.JSONResult;
import com.example.moviedb.Movie;
import com.example.moviedb.MyInterface;
import com.example.moviedb.RetrofitClientInstance;
import com.example.moviedb.screens.home.MoviesView;
import com.example.moviedb.screens.home.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MoviesView.Presenter {


    private Activity activity;
    private  MoviesView.View view;

    private ArrayList<Genre> genres;

    public MoviePresenter(Activity activity, MoviesView.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void init() {
        view.init();

        getGenre();
    }

    @Override
    public Genre getSelectedGenre(int position) {
        return genres.get(position);
    }

    public void getGenre() {
        RetrofitClientInstance.getInstance().getGenres(new Callback<GenreJSONResults>() {
            @Override
            public void onResponse(Call<GenreJSONResults> genreCall, Response<GenreJSONResults> response) {
                if (response.body() != null && response.body().getGenres().size() > 0) {
                    genres = response.body().getGenres();

                    view.onGenresReady(genres);
                    getMovies(genres.get(0).getId());
                }
            }

            @Override
            public void onFailure(Call<GenreJSONResults> genreCall, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void getMovies(String genreId) {
        RetrofitClientInstance.getInstance().getMovies(genreId, new Callback<JSONResult>() {
            @Override
            public void onResponse(Call<JSONResult>call, Response<JSONResult> response) {
                view.populateListView(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<JSONResult> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
