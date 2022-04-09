package com.thaislins.newsfeed.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.R
import com.example.newsfeed.databinding.ActivityMainBinding
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.view.adapter.NewsAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvNews.layoutManager = LinearLayoutManager(this)
        val news = ArrayList<News?>()

        news.add(News(3687314, "Will Smith banned from the Oscars for 10 years over Chris Rock slap", "Apr 08 2022"))
        news.add(News(3709955, "At least 50 killed, dozens wounded in Russian missile strike on Ukrainian train station, say officials", "Apr 08 2022"))
        news.add(News(3715700, "RCMP investigating after 'suspicious package' triggers Trans Mountain pipeline shutdown", "Apr 08 2022"))
        news.add(News(3715674, "Indian international student shot and killed at Toronto subway station", "Apr 08 2022"))

        val adapter = NewsAdapter(news)
        binding.rvNews.adapter = adapter
    }
}