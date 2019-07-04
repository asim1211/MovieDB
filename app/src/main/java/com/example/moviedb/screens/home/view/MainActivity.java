package com.example.moviedb.screens.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moviedb.Genre;
import com.example.moviedb.GenreJSONResults;
import com.example.moviedb.JSONResult;
import com.example.moviedb.Movie;
import com.example.moviedb.screens.home.adapter.MovieAdapter;
import com.example.moviedb.screens.home.adapter.SpinnerAdapter;
import com.example.moviedb.screens.home.presenter.MoviePresenter;
import com.example.moviedb.MyInterface;
import com.example.moviedb.R;
import com.example.moviedb.RetrofitClientInstance;
import com.example.moviedb.screens.home.MoviesView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class MainActivity extends AppCompatActivity implements MoviesView.View {


    private Spinner spinner1;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    public static String key = "6a454f1310829848d7744fcda5a5cb30";

    Call<GenreJSONResults> genreCall;
    List<Genre> genreList = new ArrayList<>();
    JSONResult movieList;
    private MoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnSpinnerItemSelection();

        MyInterface interactor = RetrofitClientInstance.getInstance();
        presenter = new MoviePresenter(interactor);
        presenter.bind(this);
        presenter.getGenre();
    }

    @Override
    public void addItemToSpinner(ArrayList<Genre> genresList) {

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        SpinnerAdapter dataAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, genresList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

    }

    @Override
    public void populateListView(List<Movie> moviesList) {
        recyclerView = (RecyclerView) findViewById(R.id.listings_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (adapter == null) {
            adapter = new MovieAdapter(this, moviesList);
            recyclerView.setAdapter(adapter);
        }
        adapter.updateData(moviesList);
    }

    class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                if (parent.getItemAtPosition(pos).toString().equals(genreList.get(pos).getName())) {
                    presenter.gener = Integer.parseInt(genreList.get(pos).getId());
                } else {
                    presenter.gener = 28;
                }

                presenter.getMovies(presenter.getCurrentGenre());

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }








    // add items into spinner dynamically
    public void addItemsOnSpinner(List<Genre> genresList) {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i< genresList.size(); i++){
            list.add(genresList.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }



    // changing the spinner dynamically
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }





