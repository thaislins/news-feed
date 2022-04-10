package com.thaislins.newsfeed.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thaislins.newsfeed.databinding.ActivityMainBinding
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.view.adapter.NewsAdapter
import com.thaislins.newsfeed.modules.news.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvNews.layoutManager = LinearLayoutManager(this)

        newsViewModel.loadNewsList()

        // Setup view model news observer
        val newsObserver = Observer<List<News>?> { newsList ->
            val adapter = NewsAdapter(newsList, view.context)
            binding.rvNews.adapter = adapter
        }

        newsViewModel.newsList.observe(this, newsObserver)
    }
}