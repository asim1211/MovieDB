package com.example.moviedb.screens.home.screens.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;

    public HorizontalAdapter(Context context, List<Genre> genres) {
//        super(context, 0, genres);
        this.inflater = LayoutInflater.from(context);
    }

//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View listItem = convertView;
//        if(listItem == null)
//            listItem = inflater.inflate(R.layout.adapter_genre, parent, false);
//
//        ((TextView) listItem.findViewById(R.id.genre_label)).setText(getItem(position).getName());
//        return listItem;
//    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_genre, parent, false);
        return new RecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

//    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return getView(position, convertView, parent);
//    }
}
