package com.example.sachinchandil.newsapp.dagger.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.example.sachinchandil.newsapp.database.entities.NewsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    @Inject
    fun providesDatabase(application: Application): NewsDataBase =
        Room.databaseBuilder(application, NewsDataBase::class.java, DATABASE_NAME).build()

    @Provides
    @Inject
    fun providesNewsDao(db: NewsDataBase) = db.newsDao()

    companion object {
        const val DATABASE_NAME = "news"
    }
}