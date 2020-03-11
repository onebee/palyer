package com.one.player.net

import com.one.player.util.URLProviderUtils
import com.one.player.model.MvAreaBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvAreaRequest( handler: ResponseHandler<List<MvAreaBean>>) :
    MRequest<List<MvAreaBean>>(0, URLProviderUtils.getMVareaUrl(), handler) {
}