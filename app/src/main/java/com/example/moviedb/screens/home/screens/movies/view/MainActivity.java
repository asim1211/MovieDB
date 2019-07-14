package com.example.moviedb.screens.home.screens.movies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.screens.movies.adapter.SpinnerAdapter;
import com.example.moviedb.screens.home.screens.movies.adapter.MovieAdapter;
import com.example.moviedb.screens.home.screens.movies.presenter.MoviePresenter;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.screens.movies.MoviesInterface;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MoviesInterface.View, AdapterView.OnItemSelectedListener {

    public static String key = "6a454f1310829848d7744fcda5a5cb30";

    @BindView(R.id.spinner1) Spinner spinner1;
    @BindView(R.id.listings_view) RecyclerView recyclerView;

    private MovieAdapter adapter;
    private MoviePresenter presenter;

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
        spinner1.setOnItemSelectedListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onGenresReady(ArrayList<Genre> genres) {
        SpinnerAdapter dataAdapter = new SpinnerAdapter(this, genres);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    @Override
    public void onMoviesReady(List<Movie> moviesList) {
        if (adapter == null) {
            adapter = new MovieAdapter(this, moviesList,this);
            recyclerView.setAdapter(adapter);
        }
        else
            adapter.updateData(moviesList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

        presenter.getMovies(presenter.getSelectedGenre(pos).getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onIntent(Intent intent) {
        startActivity(intent);
    }
}




