package com.example.sachinchandil.newsapp.database.entities

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [ArticlesItem::class], version = 1, exportSchema = true)
abstract class NewsDataBase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

}