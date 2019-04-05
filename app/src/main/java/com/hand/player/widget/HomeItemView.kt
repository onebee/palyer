package com.hand.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hand.player.R

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeItemView :RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {

        View.inflate(context, R.layout.item_home,this)
    }
}