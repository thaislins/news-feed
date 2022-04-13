package com.thaislins.newsfeed.modules.news.model.datasource

import com.thaislins.newsfeed.modules.news.model.News

interface NewsDataSource {

    suspend fun getNewsList(): List<News>?
}