package com.example.sachinchandil.newsapp.dagger.modules

import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class CoroutineModule {

    @Provides
    fun provideCoroutineDispatchers() = ThreadDispatcher(Dispatchers.Main, Dispatchers.Default)
}