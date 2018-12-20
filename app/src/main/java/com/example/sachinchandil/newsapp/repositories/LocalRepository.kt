package com.example.sachinchandil.newsapp.repositories

import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.database.entities.NewsDao
import javax.inject.Inject

class LocalRepository @Inject constructor(private val newsDao: NewsDao) {

    fun insertHeadLines(list: List<ArticlesItem>) {
        newsDao.insert(list)
    }

    fun fetchHeadLines() = newsDao.getAllNews()
}