package com.example.sachinchandil.newsapp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.espresso.IdlingResource
import com.meesho.supply.util.arch.viewstate.State
import com.meesho.supply.util.arch.viewstate.ViewEvent
import com.meesho.supply.util.arch.viewstate.ViewState

class NewsIdlingResource<E>(private val resourceName: String, private val viewState: LiveData<ViewEvent<ViewState<E>>>, lifecycleOwner: LifecycleOwner) : IdlingResource {

    var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        viewState.observe(lifecycleOwner, Observer {
            it?.let {
                if (it.peepData().viewMessage.state == State.SUCCESS) {
                    resourceCallback?.onTransitionToIdle()
                }
            }
        })
    }

    override fun getName(): String = resourceName

    override fun isIdleNow(): Boolean =
        if (viewState.value != null) viewState.value!!.peepData().viewMessage.state == State.SUCCESS else false

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

}
