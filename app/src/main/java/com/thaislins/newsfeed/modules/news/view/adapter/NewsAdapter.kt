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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        newsList[position]?.let { holder.bind(it) }

        val options: RequestOptions =
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

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

        fun bind(news: News) {
            val sdf = SimpleDateFormat("MMMM dd, yyyy - hh:mm:ss", Locale.CANADA)
            val date = Date(news.updatedAt)

            val then: Instant = date.toInstant()
            val now: Instant = Instant.now()
            val twelveHoursEarlier = now.minus(12, ChronoUnit.HOURS)
            val within12Hours = !then.isBefore(twelveHoursEarlier) && then.isBefore(now)

            val formattedDate = sdf.format(date)
            if (!within12Hours) {
                binding.newsDate.text = sdf.format(date)
            } else {
                val oneHourEarlier = now.minus(1, ChronoUnit.HOURS)
                val withinOneHour = !then.isBefore(oneHourEarlier) && then.isBefore(now)
                val d = Duration.between(then, now)

                if (d.toHours() == 0L) {
                    binding.newsDate.text =
                        d.toMinutes().toString() + " minutes"
                } else {
                    binding.newsDate.text =
                        d.toHours().toString() + " hours"
                }
            }
            binding.newsTitle.text = news.title
        }
    }
}