package com.hand.player.net

import com.hand.player.util.URLProviderUtils
import com.itheima.player.model.bean.MvAreaBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvAreaRequest( handler: ResponseHandler<List<MvAreaBean>>) :
    MRequest<List<MvAreaBean>>(0, URLProviderUtils.getMVareaUrl(), handler) {
}