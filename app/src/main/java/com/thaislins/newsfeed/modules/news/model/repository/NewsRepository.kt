package com.thaislins.newsfeed.modules.news.model.repository

import com.thaislins.newsfeed.modules.news.model.News
import com.thaislins.newsfeed.service.NewsService

class NewsRepository(private val newsService: NewsService) {

    suspend fun getNewsList(): List<News>? {
        return try {
            val response = newsService.getNewsList()

            if (response != null) {
                response
            } else {
                throw Exception()
            }
        } catch (ex: Exception) {
            throw Exception()
        }
    }
}