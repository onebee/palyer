package com.hand.player.adapter

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.View
import android.view.ViewGroup
import com.hand.player.model.AudioBean
import com.hand.player.widget.VbangItemView

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class VbangAdapter(content: Context?, c: Cursor?) : CursorAdapter(content, c) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        //To change body of created functions use File | Settings | File Templates.


        return VbangItemView(context)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        //To change body of created functions use File | Settings | File Templates.
        val itemView = view as VbangItemView

        val  itemBean = AudioBean.getAudioBean(cursor)

        itemView.setData(itemBean)

    }
}