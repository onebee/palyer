package com.hand.player.net

import com.hand.player.util.URLProviderUtils
import com.itheima.player.model.bean.YueDanBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
class YueDanRequest(type: Int, offset: Int, handler: ResponseHandler<YueDanBean>) :
    MRequest<YueDanBean>(type, URLProviderUtils.getYueDanUrl(offset, 20), handler) {
}