package com.one.player.presenter.impl

import com.one.player.model.MvAreaBean
import com.one.player.net.MvAreaRequest
import com.one.player.net.ResponseHandler
import com.one.player.presenter.interf.MvPresenter
import com.one.player.ui.view.MvView

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