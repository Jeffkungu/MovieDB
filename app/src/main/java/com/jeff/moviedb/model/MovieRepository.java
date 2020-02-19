package com.jeff.moviedb.model;

import android.app.Application;

import com.jeff.moviedb.R;
import com.jeff.moviedb.service.MovieDataService;
import com.jeff.moviedb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(final Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {

        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<MovieDBResponse> call = movieDataService.getPopularMovies(application.getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();

                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

        return this.mutableLiveData;
    }
}
