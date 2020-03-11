package com.one.player.presenter.impl

import com.one.player.base.BaseListPresenter.Companion.TYPE_INIT_OR_REFRESH
import com.one.player.base.BaseListPresenter.Companion.TYPE_LOAD_MORE
import com.one.player.base.BaseView
import com.one.player.net.ResponseHandler
import com.one.player.net.YueDanRequest
import com.one.player.presenter.interf.YueDanPresenter
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