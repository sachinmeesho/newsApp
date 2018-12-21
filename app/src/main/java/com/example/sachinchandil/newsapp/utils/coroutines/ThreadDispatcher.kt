package com.example.sachinchandil.newsapp.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher

class ThreadDispatcher(override val UI: CoroutineDispatcher, override val IO: CoroutineDispatcher) :
    DispatchersContract