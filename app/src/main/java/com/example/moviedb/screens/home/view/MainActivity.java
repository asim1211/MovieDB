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
import com.example.moviedb.Movie;
import com.example.moviedb.screens.home.adapter.MovieAdapter;
import com.example.moviedb.screens.home.presenter.MoviePresenter;
import com.example.moviedb.MyInterface;
import com.example.moviedb.R;
import com.example.moviedb.RetrofitClientInstance;
import com.example.moviedb.screens.home.MoviesView;


<<<<<<< Updated upstream:app/src/main/java/com/example/moviedb/MainActivity.java
public class MainActivity extends AppCompatActivity {
=======
public class MainActivity extends AppCompatActivity implements MoviesView.View {
>>>>>>> Stashed changes:app/src/main/java/com/example/moviedb/screens/home/view/MainActivity.java

    private Spinner spinner1;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    public static String key = "6a454f1310829848d7744fcda5a5cb30";
<<<<<<< Updated upstream:app/src/main/java/com/example/moviedb/MainActivity.java
    Call<GenreJSONResults> genreCall;
    List<Genre> genreList = new ArrayList<>();
    JSONResult movieList;
=======
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
    public void addItemToSpinner(List<Genre> genresList) {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i< genresList.size(); i++){
            list.add(genresList.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
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
>>>>>>> Stashed changes:app/src/main/java/com/example/moviedb/screens/home/view/MainActivity.java

    class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
<<<<<<< Updated upstream:app/src/main/java/com/example/moviedb/MainActivity.java

            if (parent.getItemAtPosition(pos).toString().equals(genreList.get(pos).getName())){
                gener = Integer.parseInt(genreList.get(pos).getId());
=======
            if (parent.getItemAtPosition(pos).toString().equals(genreList.get(pos).getName())){
                presenter.gener = Integer.parseInt(genreList.get(pos).getId());
>>>>>>> Stashed changes:app/src/main/java/com/example/moviedb/screens/home/view/MainActivity.java
            }else{
                presenter.gener = 28;
            }

<<<<<<< Updated upstream:app/src/main/java/com/example/moviedb/MainActivity.java
            getMovies();
=======
            //getMovies();
            presenter.getMovies(presenter.getCurrentGenre());
>>>>>>> Stashed changes:app/src/main/java/com/example/moviedb/screens/home/view/MainActivity.java
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

<<<<<<< Updated upstream:app/src/main/java/com/example/moviedb/MainActivity.java
    private void populateListView(List<Movie> moviesList) {
        recyclerView = (RecyclerView) findViewById(R.id.listings_view);
        adapter = new MovieAdapter(this,moviesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnSpinnerItemSelection();

        getGenre();
        getMovies();

    }

    // should be in the presenter
    private void getGenre() {
       // genreCall = myInterface.getGenre(key);
        RetrofitClientInstance.getInstance().getGenres(new Callback<GenreJSONResults>() {
            @Override
            public void onResponse(Call<GenreJSONResults> genreCall, Response<GenreJSONResults> response) {

                GenreJSONResults list = response.body();
                List<Genre> g = list.getGenres();
                genreList.addAll(g);
                addItemsOnSpinner(g);
            }

            @Override
            public void onFailure(Call<GenreJSONResults> genreCall, Throwable throwable) {
            }
        });
    }


        // should be in the presenter
    private void getMovies() {
        RetrofitClientInstance.getInstance().getMovies(gener, new Callback<JSONResult>() {
            @Override
            public void onResponse(Call<JSONResult>call, Response<JSONResult> response) {
                movieList = response.body();
                List<Movie> m = movieList.getMovies();
                populateListView(m);

            }

            @Override
            public void onFailure(Call<JSONResult> call, Throwable throwable) {
            }
        });
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


=======
>>>>>>> Stashed changes:app/src/main/java/com/example/moviedb/screens/home/view/MainActivity.java
    // changing the spinner dynamically
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }



}

