package com.one.player.ui.fragment

import com.one.player.adapter.HomeAdapter
import com.one.player.base.BaseListAdapter
import com.one.player.base.BaseListFragment
import com.one.player.base.BaseListPresenter
import com.one.player.presenter.impl.HomePresenterImp
import com.one.player.widget.HomeItemView
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }


}