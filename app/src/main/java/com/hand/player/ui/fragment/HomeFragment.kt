package com.hand.player.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
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


        sw_layout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE)
        sw_layout.setOnRefreshListener {
            loadData()
        }

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
}