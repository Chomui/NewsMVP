package com.example.newsmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.newsmvp.adapter.ArticlesAdapter;
import com.example.newsmvp.base.Injection;
import com.example.newsmvp.pojo.Article;
import com.example.newsmvp.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements OnArticleListener, SearchContract.View, SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArticlesAdapter adapter;
    private SearchView searchView;
    private SearchContract.Presenter presenter;
    private List<Article> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();

        adapter = new ArticlesAdapter(this);
        recyclerView = findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        presenter = new SearchPresenter((Injection) getApplicationContext());
    }

    @Override
    public void onArticleClick(int position) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("link", list.get(position).getUrl());
        startActivity(intent);
    }

    @Override
    public void updateUi(List<Article> list) {
        if (list.size() > 0) {
            adapter.submitList(list);
        } else {
            Toast.makeText(this, "Нет статей по данному запросу", Toast.LENGTH_SHORT).show();
        }
        this.list = list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_search, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        presenter.getArticles(this, s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
