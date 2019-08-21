package com.example.newsmvp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsmvp.OnArticleListener;
import com.example.newsmvp.R;
import com.example.newsmvp.pojo.Article;

public class ArticlesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView textView;
    private OnArticleListener onArticleLocalListener;

    ArticlesAdapterViewHolder(@NonNull View itemView, OnArticleListener listener) {
        super(itemView);
        textView = itemView.findViewById(R.id.textViewRecycler);

        this.onArticleLocalListener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onArticleLocalListener.onArticleClick(getAdapterPosition());
    }

    void bindTo(Article article) {
        textView.setText(article.getTitle());
    }
}
