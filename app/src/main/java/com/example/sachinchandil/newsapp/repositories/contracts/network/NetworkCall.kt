package com.example.sachinchandil.newsapp.repositories.contracts.network

import com.example.sachinchandil.newsapp.retrofit.entities.NewsResponse
import kotlinx.coroutines.Deferred

interface NetworkCall {
    suspend fun execute(): Deferred<NewsResponse>
}