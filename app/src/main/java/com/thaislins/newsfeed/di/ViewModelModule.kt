package com.thaislins.newsfeed.di

import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSource
import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSourceLocal
import com.thaislins.newsfeed.modules.news.model.datasource.NewsDataSourceRemote
import com.thaislins.newsfeed.modules.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}