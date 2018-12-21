package com.example.sachinchandil.newsapp.repositories.contracts.local

interface DBOperation<T, R> {
    fun execute(data: T): R
}