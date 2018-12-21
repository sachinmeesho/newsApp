package com.example.sachinchandil.newsapp.arch.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.sachinchandil.newsapp.utils.coroutines.ThreadDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import javax.inject.Inject

open class BaseViewModel @Inject constructor(val dispatcher: ThreadDispatcher) : ViewModel() {

    protected val parentJob = SupervisorJob()
    protected val scope = CoroutineScope(parentJob + dispatcher.UI)

    override fun onCleared() {
        parentJob.cancelChildren()
    }
}