package com.example.newsmvp.base;

import android.app.Application;

import com.example.newsmvp.network.ApiManager;
import com.example.newsmvp.network.RestApi;

public class App extends Application implements Injection{

    private RestApi mApi;

    @Override
    public void onCreate() {
        super.onCreate();
        mApi = new ApiManager();
    }

    @Override
    public RestApi injectRepository() {
        return mApi;
    }
}
