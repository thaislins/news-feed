package com.thaislins.newsfeed.modules.news.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class News(
    val id: Int,
    val title: String,
    val updatedAt: String,
    val typeAttributes: NewsTypeAttribute?
    //val imagePath: String
)