package com.one.player.ui.fragment

import com.one.player.adapter.YueDanAdapter
import com.one.player.base.BaseListAdapter
import com.one.player.base.BaseListFragment
import com.one.player.base.BaseListPresenter
import com.one.player.presenter.impl.YueDanPresenterImpl
import com.one.player.widget.YueDanItemView
import com.itheima.player.model.bean.YueDanBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class YueDanFragment : BaseListFragment<YueDanBean,YueDanBean.PlayListsBean,YueDanItemView>(){
    override fun getList(response: YueDanBean?): List<YueDanBean.PlayListsBean>? {
        return  response?.playLists
    }

    override fun getSpecialAdapter(): BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView> {
                return YueDanAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return YueDanPresenterImpl(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }

}