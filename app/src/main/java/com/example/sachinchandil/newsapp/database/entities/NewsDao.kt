package com.example.sachinchandil.newsapp.database.entities

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NewsDao {

    @Insert
    fun insert(list: List<ArticlesItem>)

    @Query("SELECT * FROM articles")
    fun getAllNews(): List<ArticlesItem>
}