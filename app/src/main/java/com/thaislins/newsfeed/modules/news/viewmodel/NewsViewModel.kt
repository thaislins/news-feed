package com.thaislins.newsfeed.modules.news.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.repository.NewsRepository
import com.thaislins.newsfeed.service.ApiModule
import kotlinx.coroutines.launch

class NewsViewModel() : ViewModel() {

    val service = ApiModule.getNewsService()
    private val repository = NewsRepository(service)
    private val _newsList = MutableLiveData<List<News>?>()
    val newsList: LiveData<List<News>?> = _newsList

    fun loadNewsList() {
        viewModelScope.launch {
            try {
                _newsList.value = repository.getNewsList()
            } catch (ex: Exception) {
                ex.message?.let { Log.e("Error", it) }
            }
        }
    }
}