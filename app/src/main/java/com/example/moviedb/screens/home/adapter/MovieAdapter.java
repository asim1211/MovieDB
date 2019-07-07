package com.example.moviedb.screens.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.Movie;
import com.example.moviedb.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(movies.get(position));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public void updateData(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieOverview;
        TextView movieTitle;
        TextView movieDate;
        TextView movieLanguage;
        ImageView movieImage;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.movieOverview = view.findViewById(R.id.item_movie_overview);
            this.movieTitle = view.findViewById(R.id.item_movie_title);
            this.movieDate = view.findViewById(R.id.item_movie_release_date);
            this.movieLanguage = view.findViewById(R.id.item_movie_language);
            this.movieImage = view.findViewById(R.id.item_movie_poster);
        }

        public void populate(Movie thisMovie) {
            movieOverview.setText(thisMovie.getOverview());
            movieTitle.setText(thisMovie.getTitle());
            movieDate.setText("Release Date: " + thisMovie.getRelease_date());
            movieLanguage.setText("Language: " + thisMovie.getOriginal_language());
            if(thisMovie.getPoster_path() != null && thisMovie.getPoster_path().length()>0)
            {
                Glide.with(context).load("https://image.tmdb.org/t/p/w92/" + thisMovie.getPoster_path()).into(movieImage);

            }else {
                movieImage.setImageResource(R.mipmap.ic_launcher_round);
            }
        }
    }
}
