package com.example.socialalert.News;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitinstance {

    private static Retrofit retrofit;
    private static final  String Baseurl="https://newsapi.org/";

    public static Retrofit getRetrofit() {

        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(Baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}