package com.one.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.one.player.R
import com.one.player.model.AudioBean
import kotlinx.android.synthetic.main.item_pop.view.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/12.
 */
class PopListItemView  : RelativeLayout {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {

        View.inflate(context, R.layout.item_pop,this)
    }

    fun setData(data: AudioBean) {

        title.text = data.display_name

        artist.text = data.artist




    }
}