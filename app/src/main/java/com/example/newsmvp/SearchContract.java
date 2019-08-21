package com.example.newsmvp;

import com.example.newsmvp.pojo.Article;

import java.util.List;

public interface SearchContract {
    interface View {
        void updateUi(List<Article> list);
    }

    interface Presenter {
        void getArticles(SearchContract.View view, String description);
    }
}
