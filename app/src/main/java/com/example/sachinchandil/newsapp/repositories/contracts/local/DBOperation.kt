package com.example.sachinchandil.newsapp.repositories.contracts.local

interface DBOperation<T, R> {
    suspend fun execute(data: T): R
}