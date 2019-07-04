package com.example.moviedb;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {

    private MyInterface interactor;

    List<Genre> genreList = new ArrayList<>();
    MoviesView view;
    int gener = 28;

    public MoviePresenter(MyInterface interactor) {
        this.interactor = interactor;
    }


    public void bind(MoviesView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void getGenre() {

        RetrofitClientInstance.getInstance().getGenres(new Callback<GenreJSONResults>() {
            @Override
            public void onResponse(Call<GenreJSONResults> genreCall, Response<GenreJSONResults> response) {

                GenreJSONResults list = response.body();
                List<Genre> g = list.getGenres();
                genreList.addAll(g);

                System.out.println("--------------------------1--------------------");
                System.out.println(genreList.get(1));
                System.out.println("--------------------------1--------------------");
                view.addItemToSpinner(g);
                //view.addItemToSpinner(g);
                //addItemsOnSpinner(g);
            }

            @Override
            public void onFailure(Call<GenreJSONResults> genreCall, Throwable throwable) {
            }
        });
    }



    public void getMovies() {
        JSONResult movieList;
        RetrofitClientInstance.getInstance().getMovies(gener, new Callback<JSONResult>() {
            @Override
            public void onResponse(Call<JSONResult>call, Response<JSONResult> response) {
                JSONResult movieList = response.body();
                List<Movie> m = movieList.getMovies();
                view.populateListView(m);
                //populateListView(m);

            }

            @Override
            public void onFailure(Call<JSONResult> call, Throwable throwable) {
            }
        });
    }


//    public void addItemsOnSpinner(List<Genre> genresList) {
//        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i< genresList.size(); i++){
//            list.add(genresList.get(i).getName());
//        }

}
