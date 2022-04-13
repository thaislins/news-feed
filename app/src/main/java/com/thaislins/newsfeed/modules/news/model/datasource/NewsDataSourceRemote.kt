package com.thaislins.newsfeed.modules.news.model.datasource

import com.thaislins.newsfeed.data.remote.NewsService
import com.thaislins.newsfeed.modules.news.model.News

class NewsDataSourceRemote(private val newsService: NewsService) : NewsDataSource {

    override suspend fun getNewsList(): List<News>? {
        return newsService.getNewsList()
    }
}