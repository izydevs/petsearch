package com.amit.peatsearch.Interface;


import com.amit.peatsearch.Model.PopularMovies;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GET("pouplar")
    Call<PopularMovies> getPopularMovies(@Query("api_key") String API_KEY);

    @GET("{movie_id}")
    Call<PopularMovies> getMovieDetails(@Field("movie_id") String id, @Query("api_key") String API_KEY);


}