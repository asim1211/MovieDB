package com.example.moviedb.screens.home.presenter;

import android.app.Activity;

import com.example.moviedb.screens.home.objects.Genre;
import com.example.moviedb.screens.home.networkingObjects.GenreJSONResults;
import com.example.moviedb.screens.home.networkingObjects.MovieJSONResult;
import com.example.moviedb.screens.home.objects.Movie;
import com.example.moviedb.screens.home.networking.RetrofitClientInstance;
import com.example.moviedb.screens.home.Interfaces.MoviesView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MoviesView.Presenter {


    private Activity activity;
    private  MoviesView.View view;

    private MoviesView.Presenter movieDetailsPresenter;

    private ArrayList<Genre> genres;
    private List<Movie> movies;

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
        RetrofitClientInstance.getInstance().getMovies(genreId, new Callback<MovieJSONResult>() {
            @Override
            public void onResponse(Call<MovieJSONResult>call, Response<MovieJSONResult> response) {
                movies = response.body().getMovies();
                view.populateListView(movies);
            }

            @Override
            public void onFailure(Call<MovieJSONResult> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
