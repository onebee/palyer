package com.hand.player.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hand.player.R
import com.hand.player.adapter.HomeAdapter
import com.hand.player.base.BaseFragment
import com.hand.player.util.ThreadUtil
import com.hand.player.util.URLProviderUtils
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import org.jetbrains.anko.info
import java.io.IOException

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeFragment : BaseFragment() {

    val adapter by lazy {
        HomeAdapter()
    }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        super.initListener()
        home_rv.layoutManager = LinearLayoutManager(context)
        home_rv.adapter = adapter


        sw_layout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE)
        sw_layout.setOnRefreshListener {
            loadData()
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

                            loadMoreData(adapter.itemCount - 1)
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

        loadData()

    }

    private fun loadData() {
        val path = URLProviderUtils.getHomeUrl(0, 20)

        val client = OkHttpClient.Builder().build()//addInterceptor(StethoInterceptor()).build()
        val request = Request.Builder().url(path).get().build()
        info { path }
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
//                info { " 当前线程 : " + Thread.currentThread().name }
                myToash("刷新数据失败 ! " + e.printStackTrace())

                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        sw_layout.isRefreshing = false
                    }

                })
            }

            override fun onResponse(call: Call, response: Response) {
//                info { " 当前线程 : " + Thread.currentThread().name }

                myToash("刷新数据成功 ! ")
                val result = response.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        adapter.update(list)
                        sw_layout.isRefreshing = false
                    }

                })

            }


        })


    }

    private fun loadMoreData(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset, 20)

        val client = OkHttpClient.Builder().build()//addInterceptor(StethoInterceptor()).build()
        val request = Request.Builder().url(path).get().build()
        info { path }
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
//                info { " 当前线程 : " + Thread.currentThread().name }
                myToash("刷新数据失败 ! " + e.printStackTrace())

                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        sw_layout.isRefreshing = false
                    }

                })
            }

            override fun onResponse(call: Call, response: Response) {
//                info { " 当前线程 : " + Thread.currentThread().name }

                myToash("刷新数据成功 ! ")
                val result = response.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        adapter.loadMore(list)
                        sw_layout.isRefreshing = false
                    }

                })

            }


        })


    }
}