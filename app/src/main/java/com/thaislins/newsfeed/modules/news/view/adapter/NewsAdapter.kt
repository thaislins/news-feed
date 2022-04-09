package com.thaislins.newsfeed.modules.news.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.R
import com.example.newsfeed.databinding.ItemNewsBinding
import com.thaislins.newsfeed.modules.news.model.News

class NewsAdapter(private var news: ArrayList<News?>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        news[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.newsTitle.text = news.title
            binding.newsDate.text = news.date
        }
    }
}