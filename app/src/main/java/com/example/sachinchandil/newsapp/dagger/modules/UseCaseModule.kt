package com.example.sachinchandil.newsapp.dagger.modules

import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsFetch
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsStore
import com.example.sachinchandil.newsapp.repositories.contracts.network.NewsFetch
import com.example.sachinchandil.newsapp.usecases.headlines.NewsFetchUseCase
import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideFetchDbNetworkUseCase(
        dbNewsFetch: DBNewsFetch, dbNewsStore: DBNewsStore, newsFetch: NewsFetch, dispatcher: ThreadDispatcher
    ) = NewsFetchUseCase(dbNewsFetch, dbNewsStore, newsFetch, dispatcher)
}
