package com.example.sachinchandil.newsapp.modules.headlines

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsFetch
import com.example.sachinchandil.newsapp.repositories.contracts.local.DBNewsStore
import com.example.sachinchandil.newsapp.repositories.contracts.network.NewsFetch
import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HeadLinesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var newsFetch: NewsFetch

    @Mock
    lateinit var dbNewsFetch: DBNewsFetch

    @Mock
    lateinit var dbNewsStore: DBNewsStore

    private lateinit var viewModel: HeadLinesViewModel

    @Before
    fun setUp() {

        viewModel = HeadLinesViewModel(
            newsFetch, dbNewsStore, dbNewsFetch,
            ThreadDispatcher(Dispatchers.Unconfined, Dispatchers.Unconfined)
        )

    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchHeadLines() {

        viewModel.fetchHeadLines()

        verify(dbNewsFetch).execute("ind")
    }
}