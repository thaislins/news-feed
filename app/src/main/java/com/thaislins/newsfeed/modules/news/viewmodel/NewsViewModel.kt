package com.thaislins.newsfeed.modules.news.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsList = MutableLiveData<List<News>?>()
    private var _temp = MutableLiveData<List<News>?>()
    val newsList: LiveData<List<News>?> = _temp

    fun loadNewsList() {
        viewModelScope.launch {
            try {
                _temp.value = repository.getNewsList()
                _newsList.value = _temp.value
            } catch (ex: Exception) {
                ex.message?.let { Log.e("Error", it) }
            }
        }
    }

    fun filterNewsList(type: String) {
        if (type == "all") {
            _temp.value = _newsList.value
        } else {
            _temp.value =
                _newsList.value?.filter { it.type == type }
        }
    }
}