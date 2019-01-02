package com.example.sachinchandil.newsapp.repositories.contracts.local

interface DBOperation1<P, R> {
    fun execute(data: P): R
}

interface DBOperation2<P1, P2, R> {
    fun execute(param1: P1, param2: P2): R
}

interface DBOperation0<R> {
    fun execute(): R
}
