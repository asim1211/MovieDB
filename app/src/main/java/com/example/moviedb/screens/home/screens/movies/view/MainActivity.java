package com.example.moviedb.screens.home.screens.movies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.screens.movies.adapter.HorizontalAdapter;
import com.example.moviedb.screens.home.screens.movies.adapter.MovieAdapter;
import com.example.moviedb.screens.home.screens.movies.interfaces.ItemClickListener;
import com.example.moviedb.screens.home.screens.movies.presenter.MoviePresenter;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.screens.movies.interfaces.MoviesInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements MoviesInterface.View,ItemClickListener {

    @BindView(R.id.listings_view) RecyclerView moviesRecyclerView;
    @BindView(R.id.horizontalList) RecyclerView genresRecyclerView;

    private MoviesInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MoviePresenter(this, this);
        presenter.init();
    }



    @Override
    public void init() {

        genresRecyclerView.callOnClick();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        moviesRecyclerView.setLayoutManager(mLayoutManager);

        LinearLayoutManager genreLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        genresRecyclerView.setLayoutManager(genreLayoutManager);

        moviesRecyclerView.addOnScrollListener(presenter.getScrollListener(genresRecyclerView, mLayoutManager));
    }


    @Override
    public void onMoviesReady(RealmResults<Movie> moviesList) {
        moviesRecyclerView.setAdapter(new MovieAdapter(this, moviesList,this));
    }

    @Override
    public void onScrollUpdateMovies(RealmResults<Movie> moviesList) {
        ((MovieAdapter) moviesRecyclerView.getAdapter()).updateData(moviesList);
    }

    @Override
    public void onGenresReady(List<Genre> genres) {
        HorizontalAdapter dataAdapter = new HorizontalAdapter(this, genres);
        dataAdapter.setClickListener(this);
        genresRecyclerView.setAdapter(dataAdapter);

    }


    @Override
    public void onIntent(Intent intent) {
        startActivity(intent);
    }


    @Override
    public void onItemClick(View view, int position) {
        presenter.resetVariables(position);
        String genreId = presenter.getSelectedGenre(position).getId();
        presenter.getSelectedGenre(position).selected = true;

        presenter.updateResultCondition(genreId);
        presenter.getMovies(genreId, 1);
        //view.setSelected(true);

    }
}





