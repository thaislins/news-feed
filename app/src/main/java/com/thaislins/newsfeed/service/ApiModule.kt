package com.thaislins.newsfeed.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object ApiModule {
    /**
     * Creates a retrofit object to be used for a request
     *
     */
    fun getNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbc.ca/aggregate_api/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(createLoggingInterceptor())
            .build()

        return retrofit.create(NewsService::class.java)
    }

    /**
     * Creates logging object that logs details about related request
     *
     */
    private fun createLoggingInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}