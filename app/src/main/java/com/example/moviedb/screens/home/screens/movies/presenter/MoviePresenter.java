package com.example.moviedb.screens.home.screens.movies.presenter;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.GenreJSONResults;
import com.example.moviedb.screens.home.model.MovieJSONResult;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.networking.RetrofitClientInstance;
import com.example.moviedb.screens.home.screens.movies.MoviesInterface;
import com.example.moviedb.screens.home.screens.movies.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MoviesInterface.Presenter {


    private Activity activity;
    private  MoviesInterface.View view;
    Realm realmDatabase;

    private ArrayList<Genre> genres;

    public MoviePresenter(Activity activity, MoviesInterface.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void init() {
        System.out.println("------------first--------------");
        realmDatabase.init(activity);
        realmDatabase = Realm.getDefaultInstance();


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
            public void onResponse(@NonNull Call<GenreJSONResults> genreCall, @NonNull Response<GenreJSONResults> response) {
                if (response.body() != null && response.body().getGenres().size() > 0) {
                    genres = response.body().getGenres();
                    view.onGenresReady(genres);
                    getMovies(genres.get(0).getId());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenreJSONResults> genreCall, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void getMovies(String genreId) {
        RetrofitClientInstance.getInstance().getMovies(genreId, new Callback<MovieJSONResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieJSONResult>call, @NonNull  Response<MovieJSONResult> response) {
                if (response.body() != null) {

                    System.out.println("-----------------2--------------");
                    System.out.println(response.body().getMovies().get(0).getGenre_ids().get(0));
                    saveMovies(response.body().getMovies());

                    ArrayList<Movie> movies = new ArrayList(realmDatabase.where(Movie.class).findAll());
                    view.onMoviesReady(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieJSONResult> call, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void saveMovies(List<Movie> moviesList){
        try(Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(realm1 -> {
                RealmList<Movie> realmList = new RealmList<>();
                realmList.addAll(moviesList);
                realm1.insertOrUpdate(realmList);
            });
        }


    }




}
