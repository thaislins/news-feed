package com.thaislins.newsfeed.modules.news.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thaislins.newsfeed.R
import com.thaislins.newsfeed.databinding.ItemNewsBinding
import com.thaislins.newsfeed.modules.news.model.News

class NewsAdapter(private var news: ArrayList<News?>, private var context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        news[position]?.let { holder.bind(it) }

        val options: RequestOptions =
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

        holder.newsImage?.let {
            Glide.with(context)
                .load(news[position]?.typeAttributes?.imageLarge)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .apply(options)
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        internal val newsImage: ImageView? = itemView.findViewById(R.id.newsImage)

        fun bind(news: News) {
            binding.newsTitle.text = news.title
            binding.newsDate.text = news.updatedAt
        }
    }
}