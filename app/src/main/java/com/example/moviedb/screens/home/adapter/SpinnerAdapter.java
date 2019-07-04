package com.example.moviedb.screens.home.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.moviedb.Genre;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<Genre> {


    private ArrayList<Genre> dataSet;
    Context mContext;

    public SpinnerAdapter(Context context, int resource, ArrayList<Genre> data) {
        super(context, 0,data);

        this.dataSet = data;
        this.mContext=context;

    }


    public int getGenreID(Genre spinnerGenre, Genre listGenre){

        if(spinnerGenre.getId() == listGenre.getId()){
            return Integer.parseInt(listGenre.getId());

        }else
            return 28;
    }



}
