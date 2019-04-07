package com.hand.player.ui.fragment

import com.hand.player.adapter.HomeAdapter
import com.hand.player.base.BaseListAdapter
import com.hand.player.base.BaseListFragment
import com.hand.player.base.BaseListPresenter
import com.hand.player.presenter.impl.HomePresenterImp
import com.hand.player.widget.HomeItemView
import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeFragment :BaseListFragment<List<HomeItemBean>,HomeItemBean,HomeItemView>(){
    override fun getSpecialAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()

    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return HomePresenterImp(this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {

        return  response
    }


}