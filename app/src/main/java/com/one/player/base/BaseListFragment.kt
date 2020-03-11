package com.one.player.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.one.player.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 *
 * HomeView -> BaseView
 * Adapter  -> BaseAdapter
 * Presenter -> BasePresenter
 */
abstract class BaseListFragment<RESPONSE, ITEMBEAN, ITEMVIEW : View> : BaseFragment(), BaseView<RESPONSE> {

    val adapter by lazy {
        getSpecialAdapter()
    }

    val presenter by lazy { getSpecialPresenter() }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        super.initListener()
        home_rv.layoutManager = LinearLayoutManager(context)
        home_rv.adapter = adapter


        sw_layout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE)
        sw_layout.setOnRefreshListener {
            presenter.loadData()
        }

        home_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 是否最后一条 已经显示
                    val layoutManager = home_rv.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val manager = layoutManager
                        val lastVisibleItemPosition = manager.findLastVisibleItemPosition()
                        if (lastVisibleItemPosition == adapter.itemCount - 1) {

                            presenter.loadMoreData(adapter.itemCount - 1)
                        }
                    }
                }

            }

        })

    }

    override fun loadSuccess(response: RESPONSE?) {
        myToash("加载数据成功")
        sw_layout.isRefreshing = false
        adapter.update(getList(response))

    }


    override fun loadMore(response: RESPONSE?) {
        myToash("加载更多成功")
        adapter.loadMore(getList(response))
    }

    override fun onError(message: String?) {
        myToash("加载数据失败$message")
    }


    override fun initData() {
        presenter.loadData()
    }

    /***
     * 获取适配器Adapter
     */
    abstract fun getSpecialAdapter(): BaseListAdapter<ITEMBEAN, ITEMVIEW>

    abstract fun getSpecialPresenter(): BaseListPresenter

    /***
     * 从返回集获取当前列表的方法
     */
    abstract fun getList(response: RESPONSE?): List<ITEMBEAN>?
}