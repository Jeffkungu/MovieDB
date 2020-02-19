package com.jeff.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.res.Configuration;
import android.os.Bundle;

import com.jeff.moviedb.R;
import com.jeff.moviedb.adapter.MovieAdapter;
import com.jeff.moviedb.databinding.ActivityMainBinding;
import com.jeff.moviedb.model.Movie;
import com.jeff.moviedb.model.MovieDBResponse;
import com.jeff.moviedb.service.MovieDataService;
import com.jeff.moviedb.service.RetrofitInstance;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Jeff Kungu");

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getPopularMovies();
    }

    public void getPopularMovies() {

        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<MovieDBResponse> call = movieDataService.getPopularMovies(this.getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {

                MovieDBResponse movieDBResponse = response.body();


                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    showOnRecyclerView();
                }


            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

    }

    private void showOnRecyclerView() {

        recyclerView = activityMainBinding.rvMovies;
        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }
}
