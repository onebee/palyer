package com.hand.player.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hand.player.R
import com.hand.player.adapter.YunDanAdapter
import com.hand.player.base.BaseFragment
import com.hand.player.presenter.impl.YueDanPresenterImpl
import com.hand.player.widget.YueDanView
import com.itheima.player.model.bean.YueDanBean
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class YunDanFragment : BaseFragment(), YueDanView {

    private var list = ArrayList<YueDanBean.PlayListsBean>()
    val adapter by lazy { YunDanAdapter() }

    val yueDanPresenter by lazy { YueDanPresenterImpl(this) }
    override fun initView(): View? {

        return View.inflate(context, R.layout.fragment_list, null)

    }

    override fun initListener() {
        recycleView.layoutManager = LinearLayoutManager(context)

        recycleView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun initData() {

        yueDanPresenter.loadData()


    }

    override fun onError(message: String?) {
        myToash("加载数据失败")
    }

    override fun loadSuccess(response: YueDanBean?) {
        adapter.updateList(response?.playLists)
    }

    override fun loadMore(response: YueDanBean?) {
    }
}