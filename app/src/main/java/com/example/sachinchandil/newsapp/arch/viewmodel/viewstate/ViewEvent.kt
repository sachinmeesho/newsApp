package com.meesho.supply.util.arch.viewstate

class ViewEvent<T>(private val data: T) {
    var isEventNotHandled: Boolean = true

    private fun getData(): T {
        isEventNotHandled = false
        return data
    }

    fun peepData(): T = data

    fun doIfNotHandled(f: (T) -> Unit) {
        if(isEventNotHandled) {
            f(getData())
        }
    }
}