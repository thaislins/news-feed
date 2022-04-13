package com.thaislins.newsfeed.di

import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSourceLocal
import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSourceRemote
import com.thaislins.newsfeed.modules.news.model.repository.NewsRepository
import org.koin.dsl.module

val repoModule = module {
    single { NewsRepository(NewsDataSourceRemote(get()), NewsDataSourceLocal(get())) }
}