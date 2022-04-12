package com.thaislins.newsfeed.modules.news.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thaislins.newsfeed.R
import com.thaislins.newsfeed.databinding.ItemNewsBinding
import com.thaislins.newsfeed.modules.news.model.News
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class NewsAdapter(private var newsList: List<News?>, private var context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    fun formatDate(updatedAt: Long): String {
        val sdf = SimpleDateFormat("MMMM dd, yyyy - hh:mm:ss", Locale.CANADA)

        val newsDate = Date(updatedAt).toInstant()
        val now: Instant = Instant.now()
        val twelveHoursEarlier = now.minus(12, ChronoUnit.HOURS)
        val within12Hours = !newsDate.isBefore(twelveHoursEarlier) && newsDate.isBefore(now)

        return if (within12Hours) {
            val d = Duration.between(newsDate, now)
            if (d.toHours() == 0L) {
                context.resources.getQuantityString(R.plurals.date_minutes, d.toMinutes().toInt(), d.toMinutes().toInt())
            } else {
                context.resources.getQuantityString(R.plurals.date_hours, d.toHours().toInt(), d.toHours().toInt())
            }
        } else {
            sdf.format(Date(updatedAt))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        newsList[position]?.let { holder.bind(it) }

        val options: RequestOptions =
            RequestOptions().placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)

        holder.newsDate?.text = newsList[position]?.updatedAt?.let { formatDate(it) }

        holder.newsImage?.let {
            Glide.with(context)
                .load(newsList[position]?.typeAttributes?.imageLarge)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .apply(options)
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        internal val newsImage: ImageView? = itemView.findViewById(R.id.newsImage)
        internal val newsDate: TextView? = itemView.findViewById(R.id.newsDate)

        fun bind(news: News) {
            binding.newsTitle.text = news.title
        }
    }
}