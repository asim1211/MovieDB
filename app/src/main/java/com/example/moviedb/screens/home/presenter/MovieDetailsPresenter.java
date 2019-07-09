package com.example.moviedb.screens.home.presenter;

import android.app.Activity;


import com.example.moviedb.Movie;
import com.example.moviedb.MovieDetails;
import com.example.moviedb.RetrofitClientInstance;
import com.example.moviedb.screens.home.MovieDetailsView;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsPresenter implements MovieDetailsView.Presenter {

    private Activity activity;
    private MovieDetailsView.View view;

    private MovieDetails movieDetails;
    private String movieID;




    public MovieDetailsPresenter(Activity activity, MovieDetailsView.View view){
        this.activity = activity;
        this.view = view;

    }

    @Override
    public void init() {
        view.init();

       // getMovies(movieID);

    }

    public String getMovieID(){

        return movieID;
    }


    public void getMovieDetails(String movieID) {
        System.out.println("---------1------------");
        RetrofitClientInstance.getInstance().getMovieDetails(movieID, new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                System.out.println("---------2------------");
                System.out.println(response.body().getMovieDetails().getTitle());
                //view.populateView(response.body().getMovieDetails());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
