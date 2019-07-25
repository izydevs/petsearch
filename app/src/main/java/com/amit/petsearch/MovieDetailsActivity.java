package com.amit.petsearch;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amit.petsearch.Model.ApiResponse;
import com.amit.petsearch.Model.MovieDetails;
import com.amit.petsearch.Utils.Utils;
import com.amit.petsearch.ViewModel.MovieViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.MessageFormat;

public class MovieDetailsActivity extends AppCompatActivity {

    private String movieId;
    private TextView overview;
    private TextView duration;
    private TextView releaseDate;
    private TextView rating;
    private TextView genres;
    private TextView language;
    private TextView budget;
    private TextView revenue;
    private ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent()!=null && getIntent().hasExtra("movie_id")){
            movieId = getIntent().getStringExtra("movie_id");
            Log.d("asdfg","movie id "+movieId);
        }
        checkInternetConnection();

    }

    private void checkInternetConnection() {
        if (Utils.checkInternetConnection(this)){
            initBindViews();
        }else {
            Toast.makeText(this,getResources().getString(R.string.check_internet),Toast.LENGTH_LONG).show();
        }
    }

    private void initBindViews() {
        overview = findViewById(R.id.overview);
        duration = findViewById(R.id.duration);
        releaseDate = findViewById(R.id.release_date);
        rating = findViewById(R.id.rating);
        genres = findViewById(R.id.genres);
        language = findViewById(R.id.lang);
        budget = findViewById(R.id.budget);
        revenue = findViewById(R.id.revenue);
        poster= findViewById(R.id.banner_poster);
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage(getApplicationContext().getResources().getString(R.string.movie_detail));
        progressDialog.setCancelable(false);
        progressDialog.show();
        MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovieDetails(movieId).observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse) {
                assert apiResponse != null;
                updateUI(apiResponse);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
    }

    private void updateUI(ApiResponse response) {
        if (response.isStatus()){
            showMovieDetails(response.getMovieDetails());
        }else {
            Toast.makeText(this,getResources().getString(R.string.something_wrong),Toast.LENGTH_LONG).show();
        }
    }

    private void showMovieDetails(MovieDetails details) {
        if (details!=null){
            getSupportActionBar().setTitle(details.getTitle());
            overview.setText(details.getOverview());
            duration.setText(MessageFormat.format("{0} minutes", details.getRuntime()));
            releaseDate.setText(Utils.convertTimeFormat(details.getRelease_date()));
            rating.setText(String.valueOf(details.getVote_average()));
            genres.setText(Utils.getGenresToString(details.getGenres()));
            language.setText(details.getOriginal_language());
            budget.setText(Utils.getBudget(details.getBudget()));
            revenue.setText(Utils.getRevenue(details.getRevenue()));
            setPosterImage(details.getBackdrop_path());
        }
    }

    private void setPosterImage(String posterUrl) {
        if (posterUrl!=null){
            Glide.with(this)
                    .load(Utils.BANNER_URL+posterUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.spinner_loader).into(poster);
        }else {
            poster.setImageDrawable(getResources().getDrawable(R.drawable.image_not_available));
        }
    }
}
