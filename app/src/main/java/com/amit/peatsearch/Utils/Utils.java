package com.amit.peatsearch.Utils;

import android.content.Context;
import android.net.ConnectivityManager;


public class Utils {

    public static final String API_KEY = "419ffa2d21f9e1c4e01ac4e45b4bb959";
    public static final String POSTER_URL = "https://image.tmdb.org/t/p/w500";
    public static final String BANNER_URL = "https://image.tmdb.org/t/p/original";

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
