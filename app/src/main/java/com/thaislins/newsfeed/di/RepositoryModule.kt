package com.thaislins.newsfeed.di

import com.thaislins.newsfeed.modules.news.model.repository.NewsRepository
import org.koin.dsl.module

val repoModule = module {
    single { NewsRepository(get()) }
}