package com.example.sachinchandil.newsapp.repositories.contracts.local

import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.repositories.LocalRepository
import javax.inject.Inject

class DBNewsFetch @Inject constructor(private var localRepository: LocalRepository) :
    DBOperation<String, List<ArticlesItem>> {
    override suspend fun execute(data: String): List<ArticlesItem> = localRepository.fetchHeadLines()

}