package com.example.flixtermovies.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixtermovies.R;
import com.example.flixtermovies.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    // Involves inflating (from xml to view) a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }


    // Involves populationg data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the movie at the passed in position
        Movie movie = movies.get(position);
        // Bind the movie data into the ViewHolder
        holder.bind(movie);
    }

    // Returns the total count of items
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle;
        ImageView moviePoster;
        TextView movieOverview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieOverview = itemView.findViewById(R.id.movieOverview);
            moviePoster = itemView.findViewById(R.id.moviePoster);
        }

        public void bind(Movie movie) {
            movieTitle.setText(movie.getTitle());
            movieOverview.setText(movie.getOverview());

            String imageUrl = (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                    ? movie.getPosterPath() : movie.getBackdropPath();

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.flicks_movie_placeholder)
                    .into(moviePoster);


        }
    }
}
