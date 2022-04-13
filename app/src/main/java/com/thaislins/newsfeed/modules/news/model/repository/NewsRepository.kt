package com.thaislins.newsfeed.modules.news.model.repository

import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSource
import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSourceLocal
import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSourceRemote

class NewsRepository(
    private val remoteDataSource: NewsDataSourceRemote,
    private val localDataSource: NewsDataSourceLocal
) : NewsDataSource {

    override suspend fun getNewsList(): List<News>? {
        val remoteData: List<News>?
        try {
            remoteData = remoteDataSource.getNewsList()
        } catch (ex: Exception) {
            return localDataSource.getNewsList()
        }

        if (remoteData?.isNotEmpty() == true) {
            localDataSource.saveNews(remoteData)
        }
        return remoteData
    }
}