package com.amit.peatsearch.Utils;

import com.amit.peatsearch.Interface.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient{

    public static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit!=null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}