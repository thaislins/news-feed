package com.thaislins.newsfeed.modules.news.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class News {
    val id: Int = 0
    val title: String = ""
    val updatedAt: Long = 0
    val type: String = ""
    val typeAttributes: NewsTypeAttribute? = null
}