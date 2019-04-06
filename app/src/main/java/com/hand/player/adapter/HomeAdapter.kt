package com.hand.player.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hand.player.widget.HomeItemView
import com.hand.player.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {


    private val list = ArrayList<HomeItemBean>()


    fun update(list: List<HomeItemBean>?) {
//        if (list==null)return

        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    fun loadMore(list: List<HomeItemBean>?) {

        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            1

        } else {
            0
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return if (viewType == 1) {
            HomeHolder(LoadMoreView(parent?.context))
        } else {

            HomeHolder(HomeItemView(parent?.context))
        }


    }

    override fun getItemCount(): Int {

        return list.size + 1
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        if (position == list.size) {
            return
        }
        val data = list[position]
        val itemView = holder.itemView as HomeItemView
        itemView.setData(data)

    }


    class HomeHolder(item: View) : RecyclerView.ViewHolder(item)
}