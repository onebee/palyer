package com.hand.player.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hand.player.widget.HomeItemView
import com.itheima.player.model.bean.HomeItemBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {


    private val list = ArrayList<HomeItemBean>()


    fun update(list: List<HomeItemBean>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {


        return HomeHolder(HomeItemView(parent?.context))
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val data = list.get(position)
        val itemView = holder.itemView as HomeItemView

        itemView.setData(data)

    }


    class HomeHolder(item: View) : RecyclerView.ViewHolder(item) {

    }
}