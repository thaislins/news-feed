package com.thaislins.newsfeed.modules.news.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class NewsTypeAttribute(
    val imageLarge: String
)