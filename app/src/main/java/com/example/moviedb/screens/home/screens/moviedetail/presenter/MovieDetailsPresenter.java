package com.example.moviedb.screens.home.screens.moviedetail.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.networking.RetrofitClientInstance;
import com.example.moviedb.screens.home.screens.moviedetail.MovieDetailsInterface;
import com.example.moviedb.screens.home.model.MovieDetails;
import com.example.moviedb.screens.home.utils.Constants;
import com.example.moviedb.screens.home.utils.Strings;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsPresenter implements MovieDetailsInterface.Presenter {

    private Activity activity;
    private MovieDetailsInterface.View view;

    public MovieDetailsPresenter(Activity activity, MovieDetailsInterface.View view){
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void init() {
        getMovieDetails(activity.getIntent().getStringExtra("MOVIE_ID"));

    }

    private void getMovieDetails(String movieID) {
        RealmResults<MovieDetails> movieDetails = Realm.getDefaultInstance().where(MovieDetails.class).contains("id", movieID).findAllAsync();
        movieDetails.addChangeListener((movieDetails1, changeSet) -> retrieveMovieDetails(movieDetails1));

        RetrofitClientInstance.getInstance().getMovieDetails(movieID, new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {

                saveMovieDetails(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }


    private void retrieveMovieDetails(RealmResults<MovieDetails> movieDetailsList){

        if (movieDetailsList.size() > 0) {

            new Handler(Looper.getMainLooper()).post(() -> {
                showMovieDetails(movieDetailsList.get(0));
            });
        }

    }

    private void showMovieDetails(MovieDetails movieDetails){
        if (movieDetails != null && activity != null && !activity.isDestroyed()) {
            view.onMovieReady(movieDetails);
            view.onBackDropReady(getDrawableRequest(movieDetails.getBackdrop_path()));
            view.onPosterReady(getDrawableRequest(movieDetails.getPoster_path()));
        }
    }


    private void saveMovieDetails(MovieDetails movieDetails){
        Realm.getDefaultInstance().executeTransactionAsync(realm1 -> {
            realm1.copyToRealmOrUpdate(movieDetails);
        });
    }

    private RequestBuilder<Drawable> getDrawableRequest(String imageUrl) {
        if (Strings.getInstance().isStringNotEmpty(imageUrl))
            return Glide.with(activity).load(Constants.ORIGINAL_IMAGE_URL_DOMAIN_MOVIE_DB + imageUrl).transition(DrawableTransitionOptions.withCrossFade());
        else
            return Glide.with(activity).load(R.mipmap.ic_launcher_round).transition(DrawableTransitionOptions.withCrossFade());
    }
}
