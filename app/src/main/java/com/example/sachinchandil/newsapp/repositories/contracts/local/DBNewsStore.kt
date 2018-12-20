package com.example.sachinchandil.newsapp.repositories.contracts.local

import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.repositories.LocalRepository
import javax.inject.Inject

class DBNewsStore @Inject constructor(private var localRepository: LocalRepository) :
    DBOperation<List<ArticlesItem>, Unit> {
    override suspend fun execute(data: List<ArticlesItem>) {
        localRepository.insertHeadLines(data)
    }

}