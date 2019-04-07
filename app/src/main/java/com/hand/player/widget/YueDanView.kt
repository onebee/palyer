package com.hand.player.widget

import com.itheima.player.model.bean.YueDanBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
interface YueDanView {

    fun onError(message: String?)
    fun loadSuccess(response: YueDanBean?)
    fun loadMore(response: YueDanBean?)
}