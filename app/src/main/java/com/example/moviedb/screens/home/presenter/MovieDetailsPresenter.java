package com.example.moviedb.screens.home.presenter;

import android.app.Activity;


import com.example.moviedb.screens.home.networking.RetrofitClientInstance;
import com.example.moviedb.screens.home.MovieDetailsView;
import com.example.moviedb.screens.home.objects.MovieDetails;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsPresenter implements MovieDetailsView.Presenter {

    private Activity activity;
    private MovieDetailsView.View view;


    public MovieDetailsPresenter(Activity activity, MovieDetailsView.View view){
        this.activity = activity;
        this.view = view;

    }

    @Override
    public void init(String movieID) {
        getMovieDetails(movieID);

        view.init();

    }


    public void getMovieDetails(String movieID) {
        RetrofitClientInstance.getInstance().getMovieDetails(movieID, new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                view.populateView(response.body());
            }


            @Override
            public void onFailure(Call<MovieDetails> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }


}
