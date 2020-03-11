package com.one.player.presenter.impl

import com.one.player.base.BaseListPresenter
import com.one.player.base.BaseListPresenter.Companion.TYPE_INIT_OR_REFRESH
import com.one.player.base.BaseListPresenter.Companion.TYPE_LOAD_MORE
import com.one.player.model.MvPagerBean
import com.one.player.net.MyListRequest
import com.one.player.net.ResponseHandler
import com.one.player.presenter.interf.MvListPresenter
import com.one.player.ui.view.MvListView

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvListPresenterImpl(var code: String, var mvListView: MvListView?) : MvListPresenter,
    ResponseHandler<MvPagerBean> {
    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)

    }

    override fun onSuccess(type: Int, result: MvPagerBean) {
        if (type == TYPE_INIT_OR_REFRESH) {
            mvListView?.loadSuccess(result)

        } else if (type == TYPE_LOAD_MORE) {
            mvListView?.loadMore(result)
        }
    }

    override fun loadData() {
        MyListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, code, 0, this).execute()

    }

    override fun loadMoreData(offset: Int) {
        MyListRequest(BaseListPresenter.TYPE_LOAD_MORE, code, offset, this).execute()

    }

    override fun destroyView() {
        if (mvListView != null)
            mvListView = null
    }
}