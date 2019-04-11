package com.hand.player.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hand.player.R
import com.hand.player.model.AudioBean
import kotlinx.android.synthetic.main.item_vbang.view.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class VbangItemView : RelativeLayout {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {

        View.inflate(context, R.layout.item_vbang,this)
    }

    fun setData(itemBean: AudioBean) {

        title.text = itemBean.display_name
        artist.text = itemBean.artist
        size.text = Formatter.formatFileSize(context,itemBean.size)

    }
}