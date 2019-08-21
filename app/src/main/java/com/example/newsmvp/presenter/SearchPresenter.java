package com.example.newsmvp.presenter;

import com.example.newsmvp.BuildConfig;
import com.example.newsmvp.SearchContract;
import com.example.newsmvp.base.Injection;
import com.example.newsmvp.network.RestApi;
import com.example.newsmvp.pojo.ArticlesGetRespond;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.Presenter {

    private RestApi restApi;

    public SearchPresenter(Injection injection) {
        this.restApi = injection.injectRepository();
    }

    @Override
    public void getArticles(final SearchContract.View view, String description) {
        restApi.getArticles(description, BuildConfig.API_KEY).enqueue(new Callback<ArticlesGetRespond>() {
            @Override
            public void onResponse(Call<ArticlesGetRespond> call, Response<ArticlesGetRespond> response) {
                if(response.isSuccessful()) {
                    view.updateUi(response.body().getArticles());
                } else {
                    try {
                        throw new Throwable(response.message());
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArticlesGetRespond> call, Throwable t) {
                try {
                    throw new Throwable(t);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}
