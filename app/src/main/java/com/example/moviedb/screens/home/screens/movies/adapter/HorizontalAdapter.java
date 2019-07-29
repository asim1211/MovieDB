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
    private ItemClickListener mClickListener;

    public HorizontalAdapter(Context context, List<Genre> genres) {
        this.inflater = LayoutInflater.from(context);
        this.mGenres = genres;
    }



    @NonNull
    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalAdapter.ViewHolder holder, int position) {
        holder.genreLabel.setText(mGenres.get(position).getName());
        holder.genreLabel.setTag(position);

    }



    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {


        @BindView(R.id.genre_label) TextView genreLabel;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }



    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
