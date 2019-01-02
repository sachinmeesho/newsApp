package com.meesho.supply.util.arch.viewstate

import android.support.annotation.StringRes

class ViewMessage(val state: State, @StringRes val messageRes: Int = MESSAGE_NONE) {
    companion object {
        const val MESSAGE_NONE = -1
    }
}
