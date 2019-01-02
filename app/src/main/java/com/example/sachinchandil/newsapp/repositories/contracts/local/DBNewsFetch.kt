package com.example.sachinchandil.newsapp.repositories.contracts.local

import android.arch.lifecycle.LiveData
import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.repositories.LocalRepository
import javax.inject.Inject

class DBNewsFetch @Inject constructor(private var localRepository: LocalRepository) :
    DBOperation0<LiveData<List<ArticlesItem>>> {
    override fun execute(): LiveData<List<ArticlesItem>> = localRepository.fetchHeadLines()

}
