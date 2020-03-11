package com.one.player.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.one.player.model.AudioBean
import com.one.player.widget.PopListItemView

/**
 * @author  diaokaibin@gmail.com on 2019/4/12.
 */
class PopAdapter(var list: List<AudioBean>) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView: View? = null
        if (convertView == null) {
            itemView = PopListItemView(parent?.context)
        } else {
            itemView = convertView as PopListItemView
        }
        itemView.setData(list.get(position))
        return itemView
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}