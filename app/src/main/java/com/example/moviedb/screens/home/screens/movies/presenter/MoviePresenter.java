package com.example.moviedb.screens.home.screens.movies.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.GenreJSONResults;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.model.MovieJSONResult;
import com.example.moviedb.screens.home.networking.RetrofitClientInstance;
import com.example.moviedb.screens.home.screens.movies.MoviesInterface;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MoviesInterface.Presenter {


    private Activity activity;
    private MoviesInterface.View view;

    private ArrayList<Genre> genres;
    private int pageCount;
    private boolean setAdapter;

    public MoviePresenter(Activity activity, MoviesInterface.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void init() {
        this.pageCount = 1;
        this.setAdapter = true;

        getGenre();
        view.init();
    }

    @Override
    public Genre getSelectedGenre(Spinner spinner) {
        return genres.get(spinner.getSelectedItemPosition());
    }

    @Override
    public RecyclerView.OnScrollListener getScrollListener(Spinner spinner, LinearLayoutManager linearLayoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    pageCount = (int) Math.ceil(linearLayoutManager.getItemCount()/20);
                    pageCount++;
                    getMovies(getSelectedGenre(spinner).getId(), pageCount);

                }
            }
        };
    }

    @Override
    public void resetVariables() {
        this.pageCount = 1;
        this.setAdapter = true;
    }


    private void getGenre() {
        RetrofitClientInstance.getInstance().getGenres(new Callback<GenreJSONResults>() {
            @Override
            public void onResponse(@NonNull Call<GenreJSONResults> genreCall, @NonNull Response<GenreJSONResults> response) {
                if (response.body() != null && response.body().getGenres().size() > 0) {
                    saveGenre(response.body().getGenres());
                }
            }
            @Override
            public void onFailure(@NonNull Call<GenreJSONResults> genreCall, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        RealmResults<Genre> genres = Realm.getDefaultInstance().where(Genre.class).findAllAsync();
        genres.addChangeListener((genres1, changeSet) -> retrieveGenres(genres1));

    }

    @Override
    public void getMovies(String genreId, int page) {
        RetrofitClientInstance.getInstance().getMovies(genreId, page, new Callback<MovieJSONResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieJSONResult>call, @NonNull  Response<MovieJSONResult> response) {
                if (response.body() != null) {
                    saveMovies(response.body().getMovieObjects());
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieJSONResult> call, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        if(setAdapter) {
            setAdapter = false;
            view.onMoviesReady(Realm.getDefaultInstance().where(Movie.class).contains("genre", genreId).findAllAsync());
        }else {
            view.onScrollUpdateMovies(Realm.getDefaultInstance().where(Movie.class).contains("genre", genreId).findAllAsync());
        }

    }

    private void saveMovies(List<Movie> moviesList){
        try(Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransactionAsync(realm1 -> {
                RealmList<Movie> realmList = new RealmList<>();
                realmList.addAll(moviesList);
                realm1.copyToRealmOrUpdate(realmList);
            });
        }

    }


    private void saveGenre(final ArrayList<Genre> genreList) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransactionAsync(realm1 -> {
                RealmList<Genre> realmList = new RealmList<>();
                realmList.addAll(genreList);
                realm1.copyToRealmOrUpdate(realmList);
            });
        }
    }

    private void retrieveGenres(RealmResults<Genre> genreList) {
        if (genreList.size() > 0) {
            this.genres = new ArrayList(genreList);
            new Handler(Looper.getMainLooper()).post(() -> {
                view.onGenresReady(genres);
                getMovies(genres.get(0).getId(), 1);
            });
        }
    }
    

}
