package com.one.player.adapter

import android.content.Context
import com.one.player.base.BaseListAdapter
import com.one.player.model.VideosBean
import com.one.player.widget.MvItemView

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvListAdapter: BaseListAdapter<VideosBean, MvItemView>() {
    override fun refreshView(itemView: MvItemView, data: VideosBean) {

        itemView.setData(data)


    }

    override fun getItemView(context: Context?): MvItemView {
        return MvItemView(context)
    }
}