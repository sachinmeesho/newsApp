package com.example.sachinchandil.newsapp.retrofit

import com.example.sachinchandil.newsapp.retrofit.entities.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    fun fetchNews(@Query("country") country: String): Deferred<NewsResponse>
}