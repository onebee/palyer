package com.hand.player.widget

import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 * hoem 界面和presenter 层交互
 */
interface HomeView {
    fun onError(message: String?)
    fun loadSuccess(list: List<HomeItemBean>?)
    fun loadMore(list: List<HomeItemBean>?)
}