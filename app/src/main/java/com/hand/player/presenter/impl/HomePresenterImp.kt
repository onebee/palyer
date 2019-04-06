package com.hand.player.presenter.impl

import com.hand.player.net.HomeRequest
import com.hand.player.net.NetManager
import com.hand.player.net.ResponseHandler
import com.hand.player.presenter.interf.HomePresenter
import com.hand.player.widget.HomeView
import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 * 加不加var 的区别 可以在其他方法里使用
 * 不加var 只能在init 方法里使用
 * 加了以后可以在其他方法里使用
 */
class HomePresenterImp(var homeView: HomeView) : HomePresenter {
    override fun loadData() {
        val request = HomeRequest(0, object : ResponseHandler<List<HomeItemBean>> {
            override fun onError(msg: String?) {
                homeView.onError(msg)

            }

            override fun onSuccess(result: List<HomeItemBean>) {
                homeView.loadSuccess(result)
            }

        })
        NetManager.manager.sendRequest(request)
    }

    override fun loadMoreData(offset: Int) {

        val request = HomeRequest(offset, object : ResponseHandler<List<HomeItemBean>> {
            override fun onError(msg: String?) {
                homeView.onError(msg)
            }

            override fun onSuccess(result: List<HomeItemBean>) {
                homeView.loadMore(result)
            }

        })
        NetManager.manager.sendRequest(request)
    }

}