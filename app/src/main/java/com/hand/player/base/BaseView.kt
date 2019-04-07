package com.hand.player.base

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
interface BaseView<RESPONSE> {

    fun onError(message: String?)
    fun loadSuccess(response: RESPONSE?)
    fun loadMore(response: RESPONSE?)
}