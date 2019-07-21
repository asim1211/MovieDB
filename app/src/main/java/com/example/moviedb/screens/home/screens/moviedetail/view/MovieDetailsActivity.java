package com.example.moviedb.screens.home.screens.moviedetail.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.moviedb.screens.home.model.MovieDetails;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.screens.moviedetail.MovieDetailsInterface;
import com.example.moviedb.screens.home.screens.moviedetail.presenter.MovieDetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsInterface.View {

    public MovieDetailsInterface.Presenter movieDetailsPresenter;

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
        ButterKnife.bind(this);

        this.movieDetailsPresenter = new MovieDetailsPresenter(this, this);
        movieDetailsPresenter.init();
    }

    @Override
    public void onMovieReady(MovieDetails movieDetails) {
        title.setText(movieDetails.getTitle());
        original_language.setText(String.format("%s %s", getString(R.string.language_label_movie_detail), movieDetails.getOriginal_language()));
        vote_average.setText(String.format("%s %s", getString(R.string.rating_label_movie_detail), movieDetails.getVote_average()));
        release_date.setText(String.format("%s %s", getString(R.string.date_label_movie_detail), movieDetails.getRelease_date()));
        overview.setText(movieDetails.getOverview());
    }

    @Override
    public void onBackDropReady(RequestBuilder<Drawable> requestBuilder) {
        requestBuilder.into(backdrop_path);
    }

    @Override
    public void onPosterReady(RequestBuilder<Drawable> requestBuilder) {
        requestBuilder.into(poster_path);
    }
}
