package com.amit.petsearch.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.amit.petsearch.Model.MovieDetails;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Utils {

    public static final String API_KEY = "419ffa2d21f9e1c4e01ac4e45b4bb959";
    public static final String POSTER_URL = "https://image.tmdb.org/t/p/w500";
    public static final String BANNER_URL = "https://image.tmdb.org/t/p/original";

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static String getBudget(long budget) {
        return MessageFormat.format("${0}Million", budget/1000000);
    }

    public static String getRevenue(long revenue) {
        return MessageFormat.format("${0}Million", revenue/1000000);

    }

    public static String convertTimeFormat(String yyyy_mm_dd) {
        Log.d("asdf","date "+yyyy_mm_dd);
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat targetFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(yyyy_mm_dd);
            Log.d("asdf","date "+date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);

        return formattedDate;
    }

    public static String getGenresToString(List<MovieDetails.GenresData> genresList) {
        StringBuilder genres = new StringBuilder();
        for (int i=0;i<genresList.size();i++){
            genres.append(genresList.get(i).getName()).append(", ");
        }
        return genres.deleteCharAt(genres.length()-2).toString();
    }
}
