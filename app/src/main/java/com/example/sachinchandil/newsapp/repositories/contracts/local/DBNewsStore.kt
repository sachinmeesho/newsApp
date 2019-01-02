package com.example.sachinchandil.newsapp.repositories.contracts.local

import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.repositories.LocalRepository
import javax.inject.Inject

class DBNewsStore @Inject constructor(private var localRepository: LocalRepository) :
    DBOperation1<List<ArticlesItem>, Unit> {

    override fun execute(data: List<ArticlesItem>) {
        localRepository.insertHeadLines(data)
    }

}
