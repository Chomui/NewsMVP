package com.example.newsmvp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.newsmvp.OnArticleListener;
import com.example.newsmvp.R;
import com.example.newsmvp.pojo.Article;

public class ArticlesAdapter extends ListAdapter<Article, ArticlesAdapterViewHolder> {

    private OnArticleListener onArticleGlobalListener;

    public ArticlesAdapter(OnArticleListener onArticleGlobalListener) {
        super(new DiffUtil.ItemCallback<Article>() {
            @Override
            public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }
        });
        this.onArticleGlobalListener = onArticleGlobalListener;
    }

    @NonNull
    @Override
    public ArticlesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_article_recycler, parent, false);
        return new ArticlesAdapterViewHolder(view, onArticleGlobalListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapterViewHolder holder, int position) {
        Article article = getItem(position);
        if(article != null) {
            holder.bindTo(article);
        }
    }
}
