package com.example.sachinchandil.newsapp.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersContract {
    val UI: CoroutineDispatcher
    val IO: CoroutineDispatcher
}