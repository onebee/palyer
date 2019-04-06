package com.hand.player.net

import com.hand.player.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
class NetManager private constructor() {
    private val client by lazy { OkHttpClient.Builder().build() }

    companion object {
        val manager by lazy { NetManager() }
    }

    /**
     * 发送网络请求
     * **/
    fun <RESPONSE> sendRequest(req: MRequest<RESPONSE>) {

        val request = Request.Builder().url(req.url).get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    //homeView.onError(e?.message)
                    req.handler.onError(e?.message)
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val parseResult = req.parseResult(result)

                ThreadUtil.runOnMainThread(Runnable {
                    //homeView.loadMore(list)
                    req.handler.onSuccess(parseResult)
                })
            }
        })

    }


}