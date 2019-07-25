package com.amit.peatsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.amit.peatsearch.Model.Movie;
import com.amit.peatsearch.Utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent()!=null && getIntent().hasExtra("movie_details")){
            movie = (Movie) getIntent().getSerializableExtra("movie_details");
        }
        initBindViews();
    }

    private void initBindViews() {
        ImageView movieBanner = findViewById(R.id.banner_poster);
        Glide.with(this)
                .load(Utils.BANNER_URL+movie.getBackdrop_path())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.spinner_loader)
                .into(movieBanner);

    }
}
