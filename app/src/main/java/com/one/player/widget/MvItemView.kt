package com.one.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.one.player.R
import com.one.player.model.VideosBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mv.view.*

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

        artist.text = data.artistName

        title.text = data.title

        Picasso.with(context).load(data.playListPic).into(bg)


    }
}