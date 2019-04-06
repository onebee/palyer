package com.hand.player.presenter.impl

import com.hand.player.net.HomeRequest
import com.hand.player.net.ResponseHandler
import com.hand.player.presenter.interf.HomePresenter
import com.hand.player.presenter.interf.HomePresenter.Companion.TYPE_INIT_OR_REFRESH
import com.hand.player.presenter.interf.HomePresenter.Companion.TYPE_LOAD_MORE
import com.hand.player.widget.HomeView
import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 * 加不加var 的区别 可以在其他方法里使用
 * 不加var 只能在init 方法里使用
 * 加了以后可以在其他方法里使用
 */
class HomePresenterImp(var homeView: HomeView?) : HomePresenter, ResponseHandler<List<HomeItemBean>> {
    override fun onError(type: Int, msg: String?) {
        homeView?.onError(msg)

    }

    override fun onSuccess(type: Int, result: List<HomeItemBean>) {
        when (type) {
            TYPE_LOAD_MORE -> {
                homeView?.loadMore(result)

            }
            TYPE_INIT_OR_REFRESH -> {
                homeView?.loadSuccess(result)
            }
        }

    }


    override fun loadData() {
        val request = HomeRequest(TYPE_INIT_OR_REFRESH, 0, this).execute()
//        NetManager.manager.sendRequest(request)
    }

    override fun loadMoreData(offset: Int) {

        val request = HomeRequest(TYPE_LOAD_MORE, offset, this).execute()
//        NetManager.manager.sendRequest(request)
    }


    /**
     *
     * 解绑view
     */
    fun destoryView() {

        if (homeView != null) {
            homeView = null
        }
    }

}