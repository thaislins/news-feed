package com.thaislins.newsfeed.modules.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.Resource
import com.thaislins.newsfeed.modules.news.model.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsList = MutableLiveData<Resource<List<News>?>>() // original list
    private var _temp = MutableLiveData<Resource<List<News>?>>() // list that may be changed according to filters
    val newsList: LiveData<Resource<List<News>?>> = _temp

    fun loadNewsList() {
        viewModelScope.launch {
            try {
                _temp.value = Resource(data = repository.getNewsList(), hasError = false)
            } catch (ex: Exception) {
                _temp.value = Resource(data = null, hasError = true)
            }
            _newsList.value = _temp.value
        }
    }

    fun filterNewsList(type: String) {
        if (type == "all") {
            _temp.value = _newsList.value
        } else {
            _temp.value =
                Resource(data = _newsList.value?.data?.filter { it.type == type }, hasError = _newsList.value?.hasError)
        }
    }
}