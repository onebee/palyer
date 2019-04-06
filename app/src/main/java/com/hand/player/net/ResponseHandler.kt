package com.hand.player.net

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
interface ResponseHandler<RESPONSE> {

    fun onError(type:Int,msg: String?)
    fun onSuccess(type:Int,result: RESPONSE)
}