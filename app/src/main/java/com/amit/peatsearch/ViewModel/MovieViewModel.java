package com.amit.peatsearch.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.amit.peatsearch.Interface.API;
import com.amit.peatsearch.Model.ApiResponse;
import com.amit.peatsearch.Model.PopularMovies;
import com.amit.peatsearch.Utils.APIClient;
import com.amit.peatsearch.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> myMovieResponse;

    public MutableLiveData<ApiResponse> getPopularMovie(){
        if (myMovieResponse==null){
            myMovieResponse = new MutableLiveData<>();
            loadPopularMovies();
        }
        return myMovieResponse;
    }

    private void loadPopularMovies() {
        API api = APIClient.getClient().create(API.class);
        Call<PopularMovies> call = api.getPopularMovies(Utils.API_KEY);
        call.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                if (response.body()!=null){
                    myMovieResponse.postValue(new ApiResponse(true,"success",response.body()));
                }else {
                    myMovieResponse.postValue(new ApiResponse(true,"something went wrong",null));
                }
            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {
                myMovieResponse.postValue(new ApiResponse(true,"failure",null));
            }
        });
    }
}