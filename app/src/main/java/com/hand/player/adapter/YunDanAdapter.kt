package com.hand.player.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hand.player.widget.LoadMoreView
import com.hand.player.widget.YunDanItemView
import com.itheima.player.model.bean.YueDanBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
class YunDanAdapter : RecyclerView.Adapter<YunDanAdapter.YueDanHolder>() {

    private var list = ArrayList<YueDanBean.PlayListsBean>()


    fun updateList(list: List<YueDanBean.PlayListsBean>?) {

        list?.let {

            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    fun loadMore(list: List<YueDanBean.PlayListsBean>?) {

        list?.let {

            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return if (viewType == 0) {
            YueDanHolder(YunDanItemView(parent.context))
        } else {
            YueDanHolder(LoadMoreView(parent.context))
        }
    }

    override fun getItemCount(): Int {

        return list.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            1
        } else {
            0
        }

    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {
        if(position==list.size)return

        val data = list[position]
        val itemView = holder.itemView as YunDanItemView
        itemView.setData(data)
    }


    class YueDanHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}