package com.one.player.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.one.player.widget.LoadMoreView

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
abstract class BaseListAdapter<ITEMBEAN, ITEMVIEW : View> : RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {


    private val list = ArrayList<ITEMBEAN>()


    fun update(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    fun loadMore(list: List<ITEMBEAN>?) {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder {
        return if (viewType == 1) {
            BaseListHolder(LoadMoreView(parent?.context))
        } else {

            BaseListHolder(getItemView(parent?.context))
        }


    }


    override fun getItemCount(): Int {

        return list.size + 1
    }

    override fun onBindViewHolder(holder: BaseListHolder, position: Int) {
        if (position == list.size) {
            return
        }
        val data = list[position]
        val itemView = holder.itemView as ITEMVIEW
        refreshView(itemView, data)

        itemView.setOnClickListener {
//            if (listener != null){
//                listener?.onClick(data)
//            }

            listener?.let {
                it(data)
            }


            // invoke 表示调用自己
//            listener?.invoke(data)
        }
    }


    // 定义函数类型的变量
    var listener:((itemBean:ITEMBEAN)->Unit)? =  null
    fun setMyLisenter(listener:((itemBean:ITEMBEAN)->Unit)){
        this.listener = listener
    }

//    var listener:Listener<ITEMBEAN>? = null
//
//    interface Listener<ITEMBEAN> {
//        fun onClick(data: ITEMBEAN)
//    }
//
//    fun setMyListener(listener: Listener<ITEMBEAN>) {
//        this.listener = listener
//    }

    class BaseListHolder(item: View) : RecyclerView.ViewHolder(item)

    abstract fun refreshView(itemView: ITEMVIEW, data: ITEMBEAN)

    abstract fun getItemView(context: Context?): ITEMVIEW


}