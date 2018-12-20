package com.example.sachinchandil.newsapp.database.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticlesItem(
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val source: String,
    val title: String,
    val url: String,
    val content: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}
