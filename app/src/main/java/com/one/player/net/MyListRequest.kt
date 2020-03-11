package com.one.player.net

import com.one.player.model.MvPagerBean
import com.one.player.util.URLProviderUtils

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MyListRequest(type: Int, code: String,offset:Int ,handler: ResponseHandler<MvPagerBean>) :
    MRequest<MvPagerBean>(type, URLProviderUtils.getMVListUrl(code,offset,20), handler) {
}