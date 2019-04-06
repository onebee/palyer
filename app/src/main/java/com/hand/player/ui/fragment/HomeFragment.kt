package com.hand.player.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hand.player.R
import com.hand.player.adapter.HomeAdapter
import com.hand.player.base.BaseFragment
import com.hand.player.presenter.impl.HomePresenterImp
import com.hand.player.widget.HomeView
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeFragment : BaseFragment(), HomeView {
    override fun onError(message: String?) {
        myToash("加载数据失败$message")
    }

    override fun loadSuccess(list: List<HomeItemBean>?) {
        myToash("加载数据成功")
        sw_layout.isRefreshing = false

        adapter.update(list)
    }

    override fun loadMore(list: List<HomeItemBean>?) {
        myToash("加载更多成功")
        adapter.loadMore(list)
    }

    val adapter by lazy {
        HomeAdapter()
    }

    val presenter by lazy { HomePresenterImp(this) }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        super.initListener()
        home_rv.layoutManager = LinearLayoutManager(context)
        home_rv.adapter = adapter


        sw_layout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE)
        sw_layout.setOnRefreshListener {
//            loadData()
            presenter.loadData()
        }

        home_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                when (newState) {
//                    RecyclerView.SCROLL_STATE_DRAGGING -> {
//                        info { "recycleView dragging" }
//                    }
//                    RecyclerView.SCROLL_STATE_IDLE -> {
//                        info { "recycleView idle" }
//                    }
//                    RecyclerView.SCROLL_STATE_SETTLING -> {
//                        info { "recycleView setting" }
//                    }
//                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 是否最后一条 已经显示
                    val layoutManager = home_rv.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        val manager = layoutManager
                        val lastVisibleItemPosition = manager.findLastVisibleItemPosition()
                        if (lastVisibleItemPosition == adapter.itemCount - 1) {

//                            loadMoreData(adapter.itemCount - 1)
                            presenter.loadMoreData(adapter.itemCount - 1)
                        }
                    }
                }

            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                info { "onScrolled dx=$dx  dy=$dy" }
            }


        })

    }

    override fun initData() {

//        loadData()
        presenter.loadData()


    }
}