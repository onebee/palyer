package com.one.player.adapter

import android.content.Context
import com.one.player.base.BaseListAdapter
import com.one.player.widget.YueDanItemView
import com.itheima.player.model.bean.YueDanBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
class YueDanAdapter : BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView>() {
    override fun refreshView(itemView: YueDanItemView, data: YueDanBean.PlayListsBean) {
        itemView.setData(data)

    }

    override fun getItemView(context: Context?): YueDanItemView {
        return YueDanItemView(context)
    }

}