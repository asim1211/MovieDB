package com.example.moviedb.screens.home.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviedb.MovieDetails;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.MovieDetailsView;
import com.example.moviedb.screens.home.presenter.MovieDetailsPresenter;
import com.example.moviedb.screens.home.presenter.MoviePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView.View {

    public MovieDetails movieDetails;

    public MovieDetailsPresenter movieDetailsPresenter;

    @BindView(R.id.i123) TextView m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        ButterKnife.bind(this);

        movieDetailsPresenter = new MovieDetailsPresenter(this, this);
        movieDetailsPresenter.init(getIntent().getStringExtra("MOVIE_ID"));

    }

    @Override
    public void init() {



    }



    @Override
    public void populateView(MovieDetails movieDetails) {
        System.out.println("-------134431--------------");
        System.out.println(movieDetails.getTitle());

        this.movieDetails = movieDetails;

    }
}
