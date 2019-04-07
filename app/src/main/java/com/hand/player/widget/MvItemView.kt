package com.hand.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hand.player.R
import com.hand.player.model.VideosBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvItemView : RelativeLayout {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {

        View.inflate(context, R.layout.item_mv, this)
    }


    fun setData(data: VideosBean) {


    }
}