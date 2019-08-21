package com.example.newsmvp.network;

import com.example.newsmvp.pojo.ArticlesGetRespond;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("everything")
    Call<ArticlesGetRespond> getArticles(@Query("q") String articleTitle);
}
