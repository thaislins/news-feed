package com.thaislins.newsfeed.modules.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _originalNewsList = MutableLiveData<NewsResource>()
    private val _filteredNewsList = MutableLiveData<NewsResource>()
    val newsList: LiveData<NewsResource> = _filteredNewsList

    val filteredTypeList: List<String>
        get() {
            val localFilteredTypeList = _originalNewsList.value?.data?.map { it.type } ?: emptyList()
            return (listOf("all") + localFilteredTypeList).distinct()
        }


    fun loadNewsList() {
        viewModelScope.launch {
            try {
                _originalNewsList.value = NewsResource(data = repository.getNewsList(), hasError = false)
            } catch (ex: Exception) {
                _originalNewsList.value = NewsResource(data = null, hasError = true)
            }
            _filteredNewsList.value = _originalNewsList.value
        }
    }

    fun filterNewsList(type: String) {
        if (type == "all") {
            _filteredNewsList.value = _originalNewsList.value
        } else {
            val filteredList = _originalNewsList.value?.data?.filter { it.type == type }
            _filteredNewsList.value = NewsResource(data = filteredList, hasError = _originalNewsList.value?.hasError)
        }
    }
}

class NewsResource(val data: List<News>?, val hasError: Boolean? = null)
