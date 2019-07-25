package com.amit.petsearch.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.amit.petsearch.Interface.API;
import com.amit.petsearch.Model.ApiResponse;
import com.amit.petsearch.Model.MovieDetails;
import com.amit.petsearch.Model.PopularMovies;
import com.amit.petsearch.Utils.APIClient;
import com.amit.petsearch.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> myPopularMovies;
    private MutableLiveData<ApiResponse> myMovieDetails;

    public MutableLiveData<ApiResponse> getPopularMovie(){
        if (myPopularMovies==null){
            myPopularMovies = new MutableLiveData<>();
            loadPopularMovies();
        }
        return myPopularMovies;
    }

    public MutableLiveData<ApiResponse> getMovieDetails(String movieId){
        if (myMovieDetails==null){
            myMovieDetails = new MutableLiveData<>();
            loadMovieDetails(movieId);
        }
        return myMovieDetails;
    }

    private void loadMovieDetails(String movieId) {
        API api = APIClient.getClient().create(API.class);
        Call<MovieDetails> call = api.getMovieDetails(movieId,Utils.API_KEY);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                Log.d("asdfg","success "+response.body());
                if (response.body()!=null){
                    myMovieDetails.postValue(new ApiResponse(true,"success",response.body()));
                }else {
                    myMovieDetails.postValue(new ApiResponse(false,"something went wrong"));
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.d("asdfg","failure "+t);
                myMovieDetails.postValue(new ApiResponse(false,"failure"));
            }
        });

    }

    private void loadPopularMovies() {
        API api = APIClient.getClient().create(API.class);
        Call<PopularMovies> call = api.getPopularMovies(Utils.API_KEY);
        call.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                Log.d("asdf","success "+response.body());
                if (response.body()!=null){
                    myPopularMovies.postValue(new ApiResponse(true,"success",response.body()));
                }else {
                    myPopularMovies.postValue(new ApiResponse(false,"something went wrong"));
                }
            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {
                myPopularMovies.postValue(new ApiResponse(false,"failure"));
            }
        });
    }
}