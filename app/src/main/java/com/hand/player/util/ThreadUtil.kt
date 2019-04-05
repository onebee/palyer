package com.hand.player.util

import android.os.Handler
import android.os.Looper


/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
object ThreadUtil {

    val handler = Handler(Looper.getMainLooper())

    fun runOnMainThread(runnable: Runnable) {

        handler.post(runnable)
    }
}