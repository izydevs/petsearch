package com.amit.peatsearch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amit.peatsearch.Model.Movie;
import com.amit.peatsearch.MovieDetailsActivity;
import com.amit.peatsearch.R;
import com.amit.peatsearch.Utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private List<Movie> myList;
    private Context context;

    public MovieAdapter(List<Movie> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Movie movie = myList.get(i);
//        Glide.with(context).load(Utils.URL + movie.getPoster_path()).into(myViewHolder.posterImage);
        setPosterImage(myViewHolder.posterImage, Utils.POSTER_URL+movie.getPoster_path());
        myViewHolder.title.setText(movie.getTitle());
        myViewHolder.summary.setText(movie.getOverview());
        myViewHolder.rating.setText(String.valueOf(movie.getVote_average()));
        myViewHolder.releaseDate.setText(movie.getRelease_date());
        myViewHolder.language.setText(movie.getOriginal_language().toUpperCase());

    }

    private void setPosterImage(ImageView posterImage, String poster_url) {
        Log.d("asdf","url is "+poster_url);
        Glide.with(context).load(poster_url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.spinner_loader).into(posterImage);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView posterImage;
        TextView title;
        TextView summary;
        TextView rating;
        TextView releaseDate;
        TextView language;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.poster_iv);
            title = itemView.findViewById(R.id.title_tv);
            summary = itemView.findViewById(R.id.movie_desc);
            rating = itemView.findViewById(R.id.rating);
            releaseDate = itemView.findViewById(R.id.release_date);
            language = itemView.findViewById(R.id.lang);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie_details",myList.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
