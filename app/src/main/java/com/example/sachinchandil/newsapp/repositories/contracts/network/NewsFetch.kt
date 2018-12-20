package com.example.sachinchandil.newsapp.repositories.contracts.network

import com.example.sachinchandil.newsapp.repositories.NetworkRepository
import javax.inject.Inject

class NewsFetch @Inject constructor(private var networkRepository: NetworkRepository):
    NetworkCall {
    override suspend fun execute() = networkRepository.fetchNews()
}