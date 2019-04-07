package com.hand.player.ui.fragment

import com.hand.player.adapter.YueDanAdapter
import com.hand.player.base.BaseListAdapter
import com.hand.player.base.BaseListFragment
import com.hand.player.base.BaseListPresenter
import com.hand.player.presenter.impl.YueDanPresenterImpl
import com.hand.player.widget.YueDanItemView
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