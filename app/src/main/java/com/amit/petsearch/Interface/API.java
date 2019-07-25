package com.amit.petsearch.Interface;


import com.amit.petsearch.Model.MovieDetails;
import com.amit.petsearch.Model.PopularMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GET("popular")
    Call<PopularMovies> getPopularMovies(@Query("api_key") String API_KEY);

    @GET("{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String id, @Query("api_key") String API_KEY);


}