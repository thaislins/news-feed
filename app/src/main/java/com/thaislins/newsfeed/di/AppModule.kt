package com.thaislins.newsfeed.di

import com.thaislins.newsfeed.BuildConfig.BASE_URL
import com.thaislins.newsfeed.service.NewsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val appModule = module {
    single { provideLoggingInterceptor() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideNewsService(get()) }
}

private fun provideNewsService(retrofit: Retrofit): NewsService =
    retrofit.create(NewsService::class.java)

/**
 * Creates a retrofit object to be used for a request
 *
 */
fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit
}

/**
 * Creates logging object that logs details about related request
 *
 */
private fun provideLoggingInterceptor(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}
