package com.thaislins.newsfeed.data.remote

import com.thaislins.newsfeed.modules.news.model.News
import retrofit2.http.GET

interface NewsService {
    @GET("v1/items?lineupSlug=news")
    suspend fun getNewsList(): List<News>?
}