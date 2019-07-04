package com.example.moviedb.screens.home.presenter;

import com.example.moviedb.Genre;
import com.example.moviedb.GenreJSONResults;
import com.example.moviedb.JSONResult;
import com.example.moviedb.Movie;
import com.example.moviedb.MyInterface;
import com.example.moviedb.RetrofitClientInstance;
import com.example.moviedb.screens.home.MoviesView;
import com.example.moviedb.screens.home.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MoviesView.Presenter {

    private MyInterface interactor;

    private MoviesView.View view;
    private int currentGenre;

    public MoviePresenter(MyInterface interactor) {
        this.interactor = interactor;
    }


    public void bind(MoviesView.View view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    @Override
    public int getCurrentGenre() {
        return currentGenre;
    }

    @Override
    public void setCurrentGenre(int currentGenre) {
        this.currentGenre = currentGenre;
    }

    public void getGenre() {
        RetrofitClientInstance.getInstance().getGenres(new Callback<GenreJSONResults>() {
            @Override
            public void onResponse(Call<GenreJSONResults> genreCall, Response<GenreJSONResults> response) {

                GenreJSONResults list = response.body();
                List<Genre> g = list.getGenres();

                view.addItemToSpinner(g);
            }

            @Override
            public void onFailure(Call<GenreJSONResults> genreCall, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }



    public void getMovies(int genre) {
        this.currentGenre = genre;
        RetrofitClientInstance.getInstance().getMovies(genre, new Callback<JSONResult>() {
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
