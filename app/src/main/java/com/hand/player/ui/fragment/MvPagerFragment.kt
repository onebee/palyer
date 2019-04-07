package com.hand.player.ui.fragment

import com.hand.player.adapter.MyListAdapter
import com.hand.player.base.BaseListAdapter
import com.hand.player.base.BaseListFragment
import com.hand.player.base.BaseListPresenter
import com.hand.player.model.MvPagerBean
import com.hand.player.model.VideosBean
import com.hand.player.presenter.impl.MvListPresenterImpl
import com.hand.player.ui.view.MvListView
import com.hand.player.widget.MvItemView

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvPagerFragment : BaseListFragment<MvPagerBean, VideosBean, MvItemView>(), MvListView {

    var code:String? = null

    override fun init() {

        code = arguments?.getString("args")
    }

    override fun getSpecialAdapter(): BaseListAdapter<VideosBean, MvItemView> {

        return MyListAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {

        return MvListPresenterImpl(code!!,this)
    }

    override fun getList(response: MvPagerBean?): List<VideosBean>? {
        return response?.videos
    }
}