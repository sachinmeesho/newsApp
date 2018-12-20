package com.example.sachinchandil.newsapp.retrofit.entities

data class NewsResponse(
	val totalResults: Int,
	val articles: List<ArticlesItem>,
	val status: String
)
