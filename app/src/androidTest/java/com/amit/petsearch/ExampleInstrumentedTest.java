package com.amit.petsearch.Interface;


import com.amit.petsearch.Model.MoviesDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GET("pouplar")
    Call<MoviesDetails> getPopularMovies(@Query(""));


}                                                                                                                                                                                                                                                                                                                                                                                                              