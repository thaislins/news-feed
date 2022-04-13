package com.thaislins.newsfeed.modules.news.model.datasource

import com.thaislins.newsfeed.data.local.NewsDao
import com.thaislins.newsfeed.modules.news.model.News

class NewsDataSourceLocal(private val newsDao: NewsDao) : NewsDataSource {

    override suspend fun getNewsList(): List<News>? {
        return newsDao.getAll()
    }

    fun saveNews(newsList: List<News>) {
        for (news in newsList) {
            newsDao.insertNews(news)
        }
    }
}