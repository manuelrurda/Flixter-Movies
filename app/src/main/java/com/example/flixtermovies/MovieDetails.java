package com.example.flixtermovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixtermovies.models.Movie;

import org.parceler.Parcels;

public class MovieDetails extends AppCompatActivity {

    // the movie to display
    Movie movie;

    // the view objects
    TextView mvTitle;
    TextView mvOverview;
    RatingBar mvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // resolve the view objects
        mvTitle = (TextView) findViewById(R.id.mvTitle);
        mvOverview = (TextView) findViewById(R.id.mvOverview);
        mvRating = (RatingBar) findViewById(R.id.mvRating);

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview
        mvTitle.setText(movie.getTitle());
        mvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        mvRating.setRating(voteAverage / 2.0f);

    }
}