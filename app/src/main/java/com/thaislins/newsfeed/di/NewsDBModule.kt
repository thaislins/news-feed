package com.thaislins.newsfeed.di

import android.app.Application
import androidx.room.Room
import com.thaislins.newsfeed.data.local.NewsDB
import com.thaislins.newsfeed.data.local.NewsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val newsDBModule = module {
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

fun provideDataBase(application: Application): NewsDB {
    return Room.databaseBuilder(application, NewsDB::class.java, "newsfeed_db")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideDao(database: NewsDB): NewsDao {
    return database.newsDao()
}