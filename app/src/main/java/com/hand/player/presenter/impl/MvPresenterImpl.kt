package com.hand.player.presenter.impl

import com.hand.player.net.MvAreaRequest
import com.hand.player.net.ResponseHandler
import com.hand.player.presenter.interf.MvPresenter
import com.hand.player.ui.view.MvView
import com.itheima.player.model.bean.MvAreaBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvPresenterImpl(var mvView: MvView) : MvPresenter, ResponseHandler<List<MvAreaBean>> {
    override fun onError(type: Int, msg: String?) {

        mvView.onError(msg)

    }

    override fun onSuccess(type: Int, result: List<MvAreaBean>) {
        mvView.onSuccess(result)
    }

    override fun loadDates() {

        MvAreaRequest(this).execute()

    }
}