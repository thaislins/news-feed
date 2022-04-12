package com.thaislins.newsfeed.modules.news.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class News(
    val id: Int,
    val title: String,
    val updatedAt: Long,
    val type: String,
    val typeAttributes: NewsTypeAttribute?
) {
    constructor() : this(0, "", 0, "", null)
}