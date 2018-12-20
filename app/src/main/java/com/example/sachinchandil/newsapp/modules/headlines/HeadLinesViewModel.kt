package com.example.sachinchandil.newsapp.modules.headlines

import android.arch.lifecycle.MutableLiveData
import com.example.sachinchandil.newsapp.arch.viewmodel.BaseViewModel
import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsFetch
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsStore
import com.example.sachinchandil.newsapp.repositories.contracts.network.NewsFetch
import com.meesho.supply.util.arch.viewstate.ViewEvent
import com.meesho.supply.util.arch.viewstate.ViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

class HeadLinesViewModel @Inject constructor(
    var newsFetch: NewsFetch, var dbNewsStore: DBNewsStore, var dbNewsFetch: DBNewsFetch
) :
    BaseViewModel() {

    val viewState: MutableLiveData<ViewEvent<ViewState<List<ArticlesItem>>>> =
        MutableLiveData()

    fun fetchHeadLines() {
        scope.launch {
            var articles = withContext(IO) {
                dbNewsFetch.execute("ind")
            }
            // Passing data from local cache to display in UI until new data is fetched.
            viewState.value = ViewEvent(ViewState(articles))

            var response = withContext(IO) {
                try {
                    val res = newsFetch.execute().await()
                    if(res.articles.isNotEmpty()) {
                        articles = res.articles.map { ArticlesItem(it.publishedAt, it.author, it.urlToImage,
                            it.description, it.urlToImage, it.title, it.url, it.content) }
                        dbNewsStore.execute(articles)
                    }
                    res.articles
                } catch (e: SocketTimeoutException) {
                    null
                }
            }
            if(response?.isNotEmpty() == true) {
                viewState.value = ViewEvent(ViewState(articles))
            }
        }
    }


}