package com.example.moviedb.screens.home.screens.movies.presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.GenreJSONResults;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.model.MovieJSONResult;
import com.example.moviedb.screens.home.networking.RetrofitClientInstance;
import com.example.moviedb.screens.home.screens.movies.interfaces.MoviesInterface;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MoviesInterface.Presenter {


    private Activity activity;
    private MoviesInterface.View view;

    private ArrayList<Genre> genres;
    private int pageCount;
    private int genrePosition;
    private boolean setAdapter;


    public MoviePresenter(Activity activity, MoviesInterface.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void init() {
        this.pageCount = 1;
        this.setAdapter = true;

        view.init();

        getGenre();
    }

    @Override
    public Genre getSelectedGenre(int position) {
        return genres.get(position);
    }

    @Override
    public RecyclerView.OnScrollListener getScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    pageCount = (int) Math.ceil(linearLayoutManager.getItemCount() / 20);
                    System.out.println(pageCount);
                    pageCount++;

                    getMovies(getSelectedGenre(genrePosition).getId(), pageCount);

                }
            }
        };
    }

    @Override
    public void resetVariables(int position) {
        this.pageCount = 1;
        this.genrePosition = position;
    }

    @Override
    public void getMovies(String genreId, int page) {
        RetrofitClientInstance.getInstance().getMovies(genreId, page, new Callback<MovieJSONResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieJSONResult>call, @NonNull  Response<MovieJSONResult> response) {
                if (response.body() != null)
                    saveMovies(response.body().getMovieObjects());
            }

            @Override
            public void onFailure(@NonNull Call<MovieJSONResult> call, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void updateResultCondition(String genreId) {
        if (setAdapter) {
            setAdapter = false;
            view.onMoviesReady(getMoviesResult(genreId));
        }
        else
            view.onScrollUpdateMovies(getMoviesResult(genreId));
    }

    @Override
    public void changeBackgroundColor(MoviesInterface.View view) {

    }

    private void getGenre() {
        RealmResults<Genre> genres = Realm.getDefaultInstance().where(Genre.class).findAllAsync();
        genres.addChangeListener((genres1, changeSet) -> retrieveGenres(genres1));

        RetrofitClientInstance.getInstance().getGenres(new Callback<GenreJSONResults>() {
            @Override
            public void onResponse(@NonNull Call<GenreJSONResults> genreCall, @NonNull Response<GenreJSONResults> response) {
                if (response.body() != null && response.body().getGenres().size() > 0)
                    saveGenres(response.body().getGenres());
            }

            @Override
            public void onFailure(@NonNull Call<GenreJSONResults> genreCall, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private RealmResults<Movie> getMoviesResult(String genreId) {
        return Realm.getDefaultInstance().where(Movie.class).contains("genre", genreId).findAllAsync();
    }

    private void saveMovies(List<Movie> moviesList){
        Realm.getDefaultInstance().executeTransactionAsync(realm1 -> {
            realm1.copyToRealmOrUpdate(moviesList);
        });
    }

    private void saveGenres(final ArrayList<Genre> genreList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm1 -> {
            realm1.copyToRealmOrUpdate(genreList);
        });
    }

    private void retrieveGenres(RealmResults<Genre> genreList) {
        if (genreList.size() > 0) {
            String genreId = genreList.get(0).getId();

            this.genrePosition = 0;
            this.genres = new ArrayList(genreList);
            new Handler(Looper.getMainLooper()).post(() -> {
                view.onGenresReady(genres);
                updateResultCondition(genreId);
                getMovies(genreId, 1);
            });
        }
    }
}
