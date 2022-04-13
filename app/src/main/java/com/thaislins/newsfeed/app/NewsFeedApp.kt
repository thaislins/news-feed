package com.thaislins.newsfeed.app

import android.app.Application
import com.thaislins.newsfeed.di.apiModule
import com.thaislins.newsfeed.di.newsDBModule
import com.thaislins.newsfeed.di.repoModule
import com.thaislins.newsfeed.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class NewsFeedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsFeedApp)
            modules(listOf(apiModule, newsDBModule, repoModule, viewModelModule))
        }
    }
}