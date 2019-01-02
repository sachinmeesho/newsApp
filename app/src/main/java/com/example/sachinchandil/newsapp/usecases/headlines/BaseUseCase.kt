package com.example.sachinchandil.newsapp.usecases.headlines

import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import kotlinx.coroutines.CoroutineScope

abstract class BaseUseCase<R>(protected val dispatcher: ThreadDispatcher) {
    abstract fun execute(scope: CoroutineScope): R
}
