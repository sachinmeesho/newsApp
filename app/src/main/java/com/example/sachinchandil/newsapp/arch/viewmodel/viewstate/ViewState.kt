package com.meesho.supply.util.arch.viewstate

class ViewState<T>(val data: T, val viewMessage: ViewMessage) {

    constructor(data: T): this(data, ViewMessage(State.SUCCESS, ViewMessage.MESSAGE_NONE))
    companion object {
        const val DATA_NONE = -1
    }
}