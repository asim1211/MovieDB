package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class MainActivity extends AppCompatActivity implements MoviesView {

    private Spinner spinner1;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    int gener = 28;
    public static String key = "6a454f1310829848d7744fcda5a5cb30";
    Call<GenreJSONResults> genreCall;
    public static List<Genre> genreList = new ArrayList<>();
    JSONResult movieList;
    private MoviePresenter presenter;





    @Override
    public void addItemToSpinner(List<Genre> genresList) {

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

    @Override
    public void populateListView(List<Movie> moviesList) {
//        System.out.println("--------------------------1--------------------");
//        System.out.println(moviesList.get(0).getTitle());
        recyclerView = (RecyclerView) findViewById(R.id.listings_view);
        adapter = new MovieAdapter(this,moviesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }




    class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            if (parent.getItemAtPosition(pos).toString().equals(genreList.get(pos).getName())){

                presenter.gener = Integer.parseInt(genreList.get(pos).getId());
            }else{
                presenter.gener = 28;
            }

            //getMovies();
            presenter.getMovies();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnSpinnerItemSelection();

        MyInterface interactor = RetrofitClientInstance.getInstance();
        presenter = new MoviePresenter(interactor);
        presenter.bind(this);

        //getGenre();
        presenter.getGenre();

        //getMovies();
      //  presenter.getMovies();

    }

    // changing the spinner dynamically
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }






}

