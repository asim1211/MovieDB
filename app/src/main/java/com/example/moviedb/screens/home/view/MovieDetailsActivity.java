package com.example.moviedb.screens.home.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.moviedb.screens.home.objects.MovieDetails;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.MovieDetailsView;
import com.example.moviedb.screens.home.presenter.MovieDetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView.View {

    public MovieDetailsPresenter movieDetailsPresenter;

    @BindView(R.id.backdrop) ImageView backdrop_path;
    @BindView(R.id.poster) ImageView poster_path;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.language) TextView original_language;
    @BindView(R.id.rating) TextView vote_average;
    @BindView(R.id.release_date) TextView release_date;
    @BindView(R.id.overview) TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        movieDetailsPresenter = new MovieDetailsPresenter(this, this);
        movieDetailsPresenter.init(getIntent().getStringExtra("MOVIE_ID"));

    }

    @Override
    public void init() {
        ButterKnife.bind(this);
    }

    @Override
    public void populateView(MovieDetails movieDetails) {
            title.setText(movieDetails.getTitle());
            original_language.setText("Language: " + movieDetails.getOriginal_language());
            vote_average.setText("Ratings: " + movieDetails.getVote_average());
            release_date.setText("release Date: " + movieDetails.getRelease_date());
            overview.setText(movieDetails.getOverview());

            if (movieDetails.getPoster_path() != null && movieDetails.getPoster_path().length() > 0) {
                Glide.with(this).load("https://image.tmdb.org/t/p/original/" + movieDetails.getPoster_path()).into(poster_path);
            } else {
                poster_path.setImageResource(R.mipmap.ic_launcher_round);
            }

            if (movieDetails.getBackdrop_path() != null && movieDetails.getBackdrop_path().length() > 0) {
                Glide.with(this).load("https://image.tmdb.org/t/p/original/" + movieDetails.getBackdrop_path()).into(backdrop_path);
            } else {
                backdrop_path.setImageResource(R.mipmap.ic_launcher_round);
            }

    }
}
