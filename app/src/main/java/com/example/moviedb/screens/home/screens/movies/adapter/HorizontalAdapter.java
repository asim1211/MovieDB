package com.example.moviedb.screens.home.screens.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.screens.home.model.Genre;
import com.example.moviedb.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<Genre> mGenres;

    public HorizontalAdapter(Context context, List<Genre> genres) {
//        super(context, 0, genres);
        this.inflater = LayoutInflater.from(context);
        this.mGenres = genres;
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
    public HorizontalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalAdapter.ViewHolder holder, int position) {

        holder.genreLabel.setText(mGenres.get(position).getName());

    }



    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.genre_label) TextView genreLabel;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

//    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return getView(position, convertView, parent);
//    }
}
