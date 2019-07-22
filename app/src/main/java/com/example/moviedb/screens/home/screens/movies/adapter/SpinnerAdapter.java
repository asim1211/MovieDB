package com.example.moviedb.screens.home.screens.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class SpinnerAdapter extends ArrayAdapter<Genre> {

    private LayoutInflater inflater;

    public SpinnerAdapter(Context context, List<Genre> genres) {
        super(context, 0, genres);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = inflater.inflate(R.layout.adapter_genre, parent, false);

        ((TextView) listItem.findViewById(R.id.genre_label)).setText(getItem(position).getName());
        return listItem;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
