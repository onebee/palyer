package com.hand.player.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
open class MRequest<RESPONSE>(val url: String, val handler: ResponseHandler<RESPONSE>) {
    /***
     * 解析网络请求的结果
     */
    fun parseResult(result: String?): RESPONSE {
        val json = Gson()

        // 获取泛型类型
        val type = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return json.fromJson(result, type)
    }

}