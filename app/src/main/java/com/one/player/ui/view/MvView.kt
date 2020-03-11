package com.one.player.ui.view

import com.one.player.model.MvAreaBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
interface MvView {
    fun onError(msg: String?)
    fun onSuccess(result: List<MvAreaBean>)
}