package com.hand.player.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return YueDanHolder(YunDanItemView(parent.context))
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)

    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {

        val data = list.get(position)
        val itemview = holder.itemView as YunDanItemView
        itemview.setData(data)


    }


    class YueDanHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }
}