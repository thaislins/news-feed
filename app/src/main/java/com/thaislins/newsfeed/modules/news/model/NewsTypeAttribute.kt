package com.thaislins.newsfeed.modules.news.model

import androidx.room.ColumnInfo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NewsTypeAttribute(
    @ColumnInfo(name = "imageLarge") val imageLarge: String = ""
)