package com.example.moviedb.screens.home.screens.movies.adapter;

import android.content.Intent;
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
import com.example.moviedb.screens.home.customView.CustomCardView;
import com.example.moviedb.screens.home.helper.Intents;
import com.example.moviedb.screens.home.utils.Constants;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.screens.movies.MoviesInterface;
import com.example.moviedb.screens.home.screens.moviedetail.view.MovieDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;

    private MoviesInterface.View movieView;

    public MovieAdapter(Context context, List<Movie> movies, MoviesInterface.View movieView){
        this.context = context;
        this.movies = movies;
        this.movieView = movieView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(new CustomCardView(context));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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


    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_movie_overview) TextView movieOverview;
        @BindView(R.id.item_movie_title) TextView movieTitle;
        @BindView(R.id.item_movie_release_date) TextView movieDate;
        @BindView(R.id.item_movie_language) TextView movieLanguage;
        @BindView(R.id.item_movie_poster) ImageView movieImage;

        private ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void populate(Movie thisMovie) {
            movieOverview.setText(thisMovie.getOverview());
            movieTitle.setText(thisMovie.getTitle());
            movieDate.setText(String.format("%s %s", context.getString(R.string.date_label_movie_adapter), thisMovie.getRelease_date()));
            movieLanguage.setText(String.format("%s %s", context.getString(R.string.language_label_movie_adapter), thisMovie.getOriginal_language()));

            if(thisMovie.getPoster_path() != null && thisMovie.getPoster_path().length() > 0)
                Glide.with(context).load(Constants.ORIGINAL_IMAGE_URL_DOMAIN_MOVIE_DB + thisMovie.getPoster_path()).into(movieImage);

            else
                Glide.with(context).load(R.mipmap.ic_launcher_round).into(movieImage);

            onItemClicked(thisMovie);
        }


        private void onItemClicked(Movie movie) {
            itemView.setOnClickListener(view -> {
                movieView.onIntent(Intents.getInstance().getMovieDetailIntent(context, movie.getID()));
            });
        }
    }
}
