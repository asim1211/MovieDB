package com.example.moviedb.screens.home.screens.movies.adapter;

import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.moviedb.screens.home.customView.CustomCardView;
import com.example.moviedb.screens.home.helper.Intents;
import com.example.moviedb.screens.home.model.Movie;
import com.example.moviedb.screens.home.utils.Constants;
import com.example.moviedb.R;
import com.example.moviedb.screens.home.screens.movies.interfaces.MoviesInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class MovieAdapter extends RealmRecyclerViewAdapter<Movie, MovieAdapter.ViewHolder>{
    private Context context;

    private MoviesInterface.View movieView;

    public MovieAdapter(Context context, RealmResults<Movie> movieObjects, MoviesInterface.View movieView){
        super(movieObjects, true);
        this.context = context;
        this.movieView = movieView;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(new CustomCardView(context));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder pHolder, int position) {

        pHolder.populate(getItem(position));

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

        private void populate(Movie thisMovieObject) {
            movieOverview.setText(thisMovieObject.getOverview());
            movieTitle.setText(thisMovieObject.getTitle());
            movieDate.setText(String.format("%s %s", context.getString(R.string.date_label_movie_adapter), thisMovieObject.getRelease_date()));
            movieLanguage.setText(String.format("%s %s", context.getString(R.string.language_label_movie_adapter), thisMovieObject.getOriginal_language()));

            if(thisMovieObject.getPoster_path() != null && thisMovieObject.getPoster_path().length() > 0)
                Glide.with(context).load(Constants.ORIGINAL_IMAGE_URL_DOMAIN_MOVIE_DB + thisMovieObject.getPoster_path()).transition(DrawableTransitionOptions.withCrossFade()).into(movieImage);

            else
                Glide.with(context).load(R.mipmap.ic_launcher_round).into(movieImage);

            onItemClicked(thisMovieObject);
            System.out.println("PR1");
            System.out.println("PR2");
        }


        private void onItemClicked(Movie movieObject) {
            itemView.setOnClickListener(view -> {
                movieView.onIntent(Intents.getInstance().getMovieDetailIntent(context, movieObject.getID()));
            });
        }
    }



}
