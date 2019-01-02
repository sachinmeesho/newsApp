package com.example.sachinchandil.newsapp.modules.headlines

import android.arch.lifecycle.MediatorLiveData
import com.example.sachinchandil.newsapp.R
import com.example.sachinchandil.newsapp.arch.viewmodel.BaseViewModel
import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import com.example.sachinchandil.newsapp.usecases.headlines.NewsFetchUseCase
import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import com.meesho.supply.util.arch.viewstate.State
import com.meesho.supply.util.arch.viewstate.ViewEvent
import com.meesho.supply.util.arch.viewstate.ViewMessage
import com.meesho.supply.util.arch.viewstate.ViewState
import java.net.SocketTimeoutException
import javax.inject.Inject

class HeadLinesViewModel @Inject constructor(
    private val newsFetchUseCase: NewsFetchUseCase,
    dispatcher: ThreadDispatcher
) :
    BaseViewModel(dispatcher) {

    var viewState: MediatorLiveData<ViewEvent<ViewState<List<ArticlesItem>>>> = MediatorLiveData()

    fun fetchHeadLines() {
        viewState.value = ViewEvent(ViewState(emptyList(), ViewMessage(State.LOADING, ViewMessage.MESSAGE_NONE)))
        try {
            viewState.addSource(newsFetchUseCase.execute(scope)) { items ->
                items?.let {
                    if (it.isNotEmpty()) {
                        viewState.value = ViewEvent(ViewState(it, ViewMessage(State.SUCCESS)))
                    } /*else {
                        viewState.value =
                                ViewEvent(ViewState(it, ViewMessage(State.SUCCESS, R.string.no_news_available)))
                    }*/
                }
            }
        } catch (e: SocketTimeoutException) {
            viewState.value =
                    ViewEvent(ViewState(emptyList(), ViewMessage(State.ERROR, R.string.internet_error)))
        }
    }


}
