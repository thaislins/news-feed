package com.thaislins.newsfeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thaislins.newsfeed.modules.news.model.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDB : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}