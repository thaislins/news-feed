package com.thaislins.newsfeed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thaislins.newsfeed.modules.news.model.News

@Dao
abstract class NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertNews(entity: News)

    @Query("SELECT * FROM news")
    abstract fun getAll(): List<News>
}