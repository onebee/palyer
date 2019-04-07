package com.hand.player.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

    private val adapter by lazy { YunDanAdapter() }
    private val yueDanPresenter by lazy { YueDanPresenterImpl(this) }

    override fun initView(): View? {

        return View.inflate(context, R.layout.fragment_list, null)

    }

    override fun initListener() {
        recycleView.layoutManager = LinearLayoutManager(context)

        recycleView.adapter = adapter
        adapter.notifyDataSetChanged()

        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)

        refreshLayout.setOnRefreshListener {
            yueDanPresenter.loadData()
        }

        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView?.layoutManager
                    if (layoutManager !is LinearLayoutManager) {
                        return
                    }

                    // 智能类型转换
                    val lastPos = layoutManager.findLastVisibleItemPosition()
                    if (lastPos == adapter.itemCount - 1) {
                        yueDanPresenter.loadMoreData(adapter.itemCount-1)
                    }

                }
            }
        })
    }

    override fun initData() {

        yueDanPresenter.loadData()


    }

    override fun onError(message: String?) {

        refreshLayout.isRefreshing = false
        myToash("加载数据失败")
    }

    override fun loadSuccess(response: YueDanBean?) {
        refreshLayout.isRefreshing = false
        myToash("加载数据成功")
        adapter.updateList(response?.playLists)
    }

    override fun loadMore(response: YueDanBean?) {
        refreshLayout.isRefreshing = false
        myToash("加载更多成功")
        adapter.loadMore(response?.playLists)
    }
}