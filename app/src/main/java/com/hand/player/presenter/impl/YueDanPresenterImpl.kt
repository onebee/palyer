package com.hand.player.presenter.impl

import com.hand.player.base.BaseListPresenter.Companion.TYPE_INIT_OR_REFRESH
import com.hand.player.base.BaseListPresenter.Companion.TYPE_LOAD_MORE
import com.hand.player.base.BaseView
import com.hand.player.net.ResponseHandler
import com.hand.player.net.YueDanRequest
import com.hand.player.presenter.interf.YueDanPresenter
import com.itheima.player.model.bean.YueDanBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class YueDanPresenterImpl(var yuedanView: BaseView<YueDanBean>?) : YueDanPresenter, ResponseHandler<YueDanBean> {
    override fun destroyView() {
        if (yuedanView != null)
            yuedanView = null

    }

    override fun onError(type: Int, msg: String?) {

        yuedanView?.onError(msg)

    }

    override fun onSuccess(type: Int, result: YueDanBean) {

        if (type == TYPE_INIT_OR_REFRESH) {
            yuedanView?.loadSuccess(result)

        } else {
            yuedanView?.loadMore(result)
        }


    }

    override fun loadData() {

        YueDanRequest(TYPE_INIT_OR_REFRESH,0,this).execute()

    }

    override fun loadMoreData(offset: Int) {
        YueDanRequest(TYPE_LOAD_MORE,offset,this).execute()
    }
}