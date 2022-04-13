package com.thaislins.newsfeed.modules.news.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class News(
    @PrimaryKey val id: Int,
    val title: String,
    val updatedAt: Long,
    val type: String,
    @Embedded val typeAttributes: NewsTypeAttribute?
) {
    constructor() : this(0, "", 0, "", null)
}