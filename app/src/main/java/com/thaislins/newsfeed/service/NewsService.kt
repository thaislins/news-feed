package com.thaislins.newsfeed.service

import com.thaislins.newsfeed.modules.news.model.News
import retrofit2.http.GET

interface NewsService {
    @GET("v1/items/")
    suspend fun getNewsList(): List<News>?
}