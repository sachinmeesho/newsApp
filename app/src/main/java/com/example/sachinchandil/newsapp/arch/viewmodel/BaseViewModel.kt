package com.example.sachinchandil.newsapp.arch.viewmodel

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel: ViewModel() {

    protected val parentJob = Job()
    protected val scope = CoroutineScope(parentJob + Dispatchers.Main)

    override fun onCleared() {
        parentJob.cancel()
    }
}