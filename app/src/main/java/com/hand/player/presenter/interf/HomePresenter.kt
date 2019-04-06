package com.hand.player.presenter.interf

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
interface HomePresenter {

    companion object {
        const val TYPE_INIT_OR_REFRESH = 1
        const val TYPE_LOAD_MORE = 2
    }

    fun loadData()
    fun loadMoreData(offset: Int)
}