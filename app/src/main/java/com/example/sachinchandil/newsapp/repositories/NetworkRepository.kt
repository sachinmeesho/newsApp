package com.example.sachinchandil.newsapp.repositories

import com.example.sachinchandil.newsapp.retrofit.NewsApiService
import com.example.sachinchandil.newsapp.retrofit.entities.NewsResponse
import kotlinx.coroutines.Deferred

class NetworkRepository(var newsApiService: NewsApiService) {

    fun fetchNews(): Deferred<NewsResponse> = newsApiService.fetchNews("ind")
}