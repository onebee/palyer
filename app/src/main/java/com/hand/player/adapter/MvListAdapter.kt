package com.hand.player.adapter

import android.content.Context
import com.hand.player.base.BaseListAdapter
import com.hand.player.model.VideosBean
import com.hand.player.widget.MvItemView

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