package com.example.flixtermovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixtermovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "c008fd1948ccc56003222c66c1eefe0d";
    private static final String NOW_PLAYING_URL = String.format("https://api.themoviedb.org/3/movie/now_playing?api_key=%s", API_KEY);
    private static final String TAG = "Main Activity";

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.d(TAG, "Results: " + results);
                    movies = Movie.fromJsonArray(results);
                    Log.d(TAG, "Movies: " + movies.size());
                } catch (JSONException e) {
                    Log.d(TAG, "Error, json exception: ", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }
}