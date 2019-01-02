package com.example.sachinchandil.newsapp.modules.headlines

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.repositories.NetworkRepository
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsFetch
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsStore
import com.example.sachinchandil.newsapp.repositories.contracts.network.NewsFetch
import com.example.sachinchandil.newsapp.retrofit.entities.NewsResponse
import com.example.sachinchandil.newsapp.retrofit.entities.Source
import com.example.sachinchandil.newsapp.usecases.headlines.NewsFetchUseCase
import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import com.meesho.supply.util.arch.viewstate.ViewEvent
import com.meesho.supply.util.arch.viewstate.ViewState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HeadLinesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var newsFetch: NewsFetch

    @Mock
    lateinit var dbNewsFetch: DBNewsFetch

    @Mock
    lateinit var dbNewsStore: DBNewsStore

    @Mock
    lateinit var networkRepository: NetworkRepository


    val captor: ArgumentCaptor<ViewEvent<ViewState<List<ArticlesItem>>>> =
        ArgumentCaptor.forClass(ViewEvent::class.java) as ArgumentCaptor<ViewEvent<ViewState<List<ArticlesItem>>>>

    private lateinit var newsList: MutableList<ArticlesItem>
    private lateinit var newsList2: List<com.example.sachinchandil.newsapp.retrofit.entities.ArticlesItem>
    private lateinit var viewModel: HeadLinesViewModel
    private lateinit var dispatcher: ThreadDispatcher

    @Before
    fun setUp() = runBlocking {
        setupInput()
        dispatcher = ThreadDispatcher(Dispatchers.Unconfined, Dispatchers.Unconfined)

        val liveDataDb: MutableLiveData<List<ArticlesItem>> = MutableLiveData()
        liveDataDb.value = newsList
        Mockito.`when`(dbNewsFetch.execute()).thenReturn(liveDataDb)

        newsFetch = object : NewsFetch(networkRepository) {
            override suspend fun execute(): Deferred<NewsResponse> =
                CompletableDeferred(NewsResponse(2, newsList2, "ok"))
        }

        viewModel = HeadLinesViewModel(
            NewsFetchUseCase(dbNewsFetch, dbNewsStore, newsFetch, dispatcher), dispatcher
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchHeadLines() {

        viewModel.viewState =
                Mockito.mock(MediatorLiveData::class.java) as MediatorLiveData<ViewEvent<ViewState<List<ArticlesItem>>>>

        viewModel.fetchHeadLines()

        verify(viewModel.viewState, times(1)).value = captor.capture()
        assertEquals(1, captor.allValues.size)
        var list = captor.allValues[0].peepData().data
        assertEquals(2, list.size)
        assertEquals(newsList[0].toString(), list[0].toString())
    }

    private fun setupInput() {
        newsList = mutableListOf()
        newsList.add(
            ArticlesItem(
                "2018-12-25T10:52:22Z", "Ryan Gaydos",
                "https://static.foxnews.com/foxnews.com/content/uploads/2018/12/NFL-Dwayne-Harris.jpg",
                "The Oakland Raiders got off to a hot start Monday night in their 27-14 win over the Denver Broncos.",
                "Fox News", "Oakland Raiders' Dwayne Harris returns punt 99 yards for touchdown - Fox News",
                "https://www.foxnews.com/sports/oakland-raiders-dwayne-harris-returns-punt-99-yards-for-touchdown",
                "content"
            )
        )

        newsList.add(
            ArticlesItem(
                "2018-12-25T10:52:22Z", "Ryan Gaydos 2",
                "https://static.foxnews.com/foxnews.com/content/uploads/2018/12/NFL-Dwayne-Harris.jpg",
                "The Oakland Raiders got off to a hot start Monday night in their 27-14 win over the Denver Broncos.",
                "Fox News", "Oakland Raiders' Dwayne Harris returns punt 99 yards for touchdown - Fox News",
                "https://www.foxnews.com/sports/oakland-raiders-dwayne-harris-returns-punt-99-yards-for-touchdown",
                "content"
            )
        )

        newsList2 = newsList.map {
            com.example.sachinchandil.newsapp.retrofit.entities.ArticlesItem(
                it.publishedAt,
                it.author, it.urlToImage, it.description, Source(it.source, ""), it.title, it.url, it.content
            )
        }
    }
}

