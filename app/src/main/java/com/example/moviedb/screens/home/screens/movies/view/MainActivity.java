package com.example.moviedb.screens.home.screens.movies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.content.Intent;

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
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements MoviesInterface.View, AdapterView.OnItemSelectedListener {

    public static String key = "6a454f1310829848d7744fcda5a5cb30";

    @BindView(R.id.spinner1) Spinner spinner1;
    @BindView(R.id.listings_view) RecyclerView recyclerView;
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
    public void onMoviesReady(RealmResults<Movie> moviesList) {
        recyclerView.setAdapter(new MovieAdapter(this, moviesList,this));
    }

    @Override
    public void onGenresReady(List<Genre> genres) {
        SpinnerAdapter dataAdapter = new SpinnerAdapter(this, genres);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
        presenter.getMovies(presenter.getSelectedGenre(pos).getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onIntent(Intent intent) {
        startActivity(intent);
    }








}





