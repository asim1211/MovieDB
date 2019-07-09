package com.example.moviedb.screens.home.view;

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

import com.example.moviedb.Genre;
import com.example.moviedb.Movie;
import com.example.moviedb.screens.home.SpinnerAdapter;
import com.example.moviedb.screens.home.adapter.MovieAdapter;
import com.example.moviedb.screens.home.presenter.MoviePresenter;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.MoviesView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MoviesView.View, AdapterView.OnItemSelectedListener {

    public static String key = "6a454f1310829848d7744fcda5a5cb30";

    @BindView(R.id.spinner1) Spinner spinner1;
    private RecyclerView recyclerView;

    private MovieAdapter adapter;

    private MoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        presenter = new MoviePresenter(this, this);
        presenter.init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this, this);
        spinner1.setOnItemSelectedListener(this);

        this.recyclerView = findViewById(R.id.listings_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onGenresReady(ArrayList<Genre> genres) {
        SpinnerAdapter dataAdapter = new SpinnerAdapter(this, genres);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    @Override
    public void populateListView(List<Movie> moviesList) {
        if (adapter == null) {
            adapter = new MovieAdapter(this, presenter, moviesList);
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





