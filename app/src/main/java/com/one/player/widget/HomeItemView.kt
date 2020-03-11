package com.one.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.one.player.R
import com.itheima.player.model.bean.HomeItemBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

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

    fun setData(data: HomeItemBean) {
        title.setText(data.title)
        desc.setText(data.description)

        Picasso.with(context).load(data.posterPic).into(bg)
    }
}