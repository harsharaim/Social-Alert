package com.example.socialalert.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<NewsModel> getallnews(@Url String url);


}
