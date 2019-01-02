package com.example.sachinchandil.newsapp.usecases.headlines

import android.arch.lifecycle.LiveData
import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsFetch
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsStore
import com.example.sachinchandil.newsapp.repositories.contracts.network.NewsFetch
import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsFetchUseCase(
    private val dbNewsFetch: DBNewsFetch,
    private val dbNewsStore: DBNewsStore,
    private val newsFetch: NewsFetch,
    dispatcher: ThreadDispatcher
) : BaseUseCase<LiveData<List<ArticlesItem>>>(dispatcher) {

    override fun execute(scope: CoroutineScope): LiveData<List<ArticlesItem>> {
        val res = dbNewsFetch.execute()
        scope.launch {
            val res = withContext(dispatcher.IO) { newsFetch.execute().await() }
            if (res.status == "ok" && res.totalResults > 0) {
                withContext(dispatcher.IO) { dbNewsStore.execute(map(res.articles)) }
            }
        }
        return res
    }

    private fun map(items: List<com.example.sachinchandil.newsapp.retrofit.entities.ArticlesItem>): List<ArticlesItem> {
        return items.map {
            ArticlesItem(
                it.publishedAt, it.author, it.urlToImage,
                it.description, it.source.name, it.title, it.url, it.content
            )
        }
    }

}
