package com.hand.player.presenter.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hand.player.presenter.interf.HomePresenter
import com.hand.player.util.ThreadUtil
import com.hand.player.util.URLProviderUtils
import com.hand.player.widget.HomeView
import com.itheima.player.model.bean.HomeItemBean
import okhttp3.*
import java.io.IOException

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 * 加不加var 的区别 可以在其他方法里使用
 * 不加var 只能在init 方法里使用
 * 加了以后可以在其他方法里使用
 */
class HomePresenterImp(var homeView: HomeView) : HomePresenter {
    override fun loadData() {
        val path = URLProviderUtils.getHomeUrl(0, 20)
//        val client = OkHttpClient.Builder().addInterceptor(StethoInterceptor()).build()//addInterceptor(StethoInterceptor()).build()
        val client = OkHttpClient.Builder().build()//addInterceptor(StethoInterceptor()).build()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        // 回到view 层处理
                        homeView.onError(e?.message)
                    }
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        homeView.loadSuccess(list)
                    }
                })
            }
        })
    }

    override fun loadMoreData(offset: Int) {

    }

}