package com.example.sayed.myapplication.data.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sayed on 19/01/2019.
 */

public class RetrofitClient {

    public static final String BASE_URL="http://ipda3.com/blood-bank/api/v1/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){

        if(retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }
}
