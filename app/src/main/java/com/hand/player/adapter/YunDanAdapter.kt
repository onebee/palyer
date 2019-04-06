package com.hand.player.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hand.player.widget.YunDanItemView

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
class YunDanAdapter : RecyclerView.Adapter<YunDanAdapter.YueDanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return YueDanHolder(YunDanItemView(parent.context))
    }

    override fun getItemCount(): Int {

        return 3
    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {
    }


    class YueDanHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }
}