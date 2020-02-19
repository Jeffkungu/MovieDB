package com.jeff.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import androidx.databinding.DataBindingUtil;
=======
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
>>>>>>> 2fea533e8da2fa2e49af370f3fc6bfcbb1fec2a6
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
import com.jeff.moviedb.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
<<<<<<< HEAD
    private ActivityMainBinding activityMainBinding;
=======
    private MainActivityViewModel mainActivityViewModel;
>>>>>>> 2fea533e8da2fa2e49af370f3fc6bfcbb1fec2a6

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Jeff Kungu");

<<<<<<< HEAD
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
=======
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
>>>>>>> 2fea533e8da2fa2e49af370f3fc6bfcbb1fec2a6

        getPopularMovies();
    }

    public void getPopularMovies() {
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                showOnRecyclerView();
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
