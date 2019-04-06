package com.hand.player.net

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
interface ResponseHandler<RESPONSE> {

    fun onError(msg: String?)
    fun onSuccess(result: RESPONSE)
}