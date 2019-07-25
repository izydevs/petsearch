package com.amit.petsearch.Model;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private boolean status;
    private String message;
    private PopularMovies popularMovies;
    private MovieDetails movieDetails;

    public ApiResponse(boolean status, String message, PopularMovies popularMovies) {
        this.status = status;
        this.message = message;
        this.popularMovies = popularMovies;
    }

    public ApiResponse(boolean status, String message, MovieDetails movieDetails) {
        this.status = status;
        this.message = message;
        this.movieDetails = movieDetails;
    }

    public ApiResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PopularMovies getPopularMovies() {
        return popularMovies;
    }

    public void setPopularMovies(PopularMovies popularMovies) {
        this.popularMovies = popularMovies;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }
}
