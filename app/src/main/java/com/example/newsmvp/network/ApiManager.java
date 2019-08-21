package com.example.newsmvp.network;

import com.example.newsmvp.pojo.ArticlesGetRespond;

import retrofit2.Call;

public class ApiManager implements RestApi{

    private RestApi api;

    public ApiManager() {
        api = NetManager.getInstance().getRestApi();
    }

    @Override
    public Call<ArticlesGetRespond> getArticles(String description) {
        return api.getArticles(description);
    }
}
