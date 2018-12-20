package com.example.sachinchandil.newsapp.dagger.modules

import com.example.sachinchandil.newsapp.repositories.NetworkRepository
import com.example.sachinchandil.newsapp.retrofit.NewsApiService
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class RepositoryModule {

    @Provides
    @Inject
    fun providesRemoteRepository(githubService: NewsApiService): NetworkRepository {
        return NetworkRepository(githubService)
    }
}