package com.hand.player.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hand.player.R
import com.itheima.player.model.bean.YueDanBean
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

import kotlinx.android.synthetic.main.item_yuedan.view.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/6.
 */
class YunDanItemView : RelativeLayout {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_yuedan, this)
    }

    fun setData(data: YueDanBean.PlayListsBean) {
        title.text = data.title
        author_name.text = data.creator?.nickName
        count.text = data.videoCount.toString()

        Picasso.with(context).load(data.playListBigPic).into(bg)

        // TODO avatar 不显示
        Picasso.with(context).load(data.creator?.largeAvatar)
            .transform(CropCircleTransformation())
            .into(author_image)

    }
}