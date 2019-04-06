package com.hand.player.net

import com.hand.player.util.URLProviderUtils
import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
class HomeRequest(  type:Int,offset: Int, handler: ResponseHandler<List<HomeItemBean>>) :
    MRequest<List<HomeItemBean>>(type,URLProviderUtils.getHomeUrl(offset, 20), handler)