package com.thaislins.newsfeed.modules.news.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thaislins.newsfeed.databinding.ActivityMainBinding
import com.thaislins.newsfeed.modules.news.view.adapter.NewsAdapter
import com.thaislins.newsfeed.modules.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.rvNews.layoutManager = LinearLayoutManager(this)

        newsViewModel.newsList.observe(this, Observer {
            if (it != null) {
                val adapter = NewsAdapter(it, view.context)
                binding.rvNews.adapter = adapter
            }
        })
    }
}