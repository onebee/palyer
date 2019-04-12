package com.hand.player.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import com.hand.player.R

/**
 * @author  diaokaibin@gmail.com on 2019/4/12.
 */
class PlayListPopWindow(cx: Context) : PopupWindow() {

    init {
        val view = LayoutInflater.from(cx).inflate(R.layout.pop_playlist, null, false)
        contentView = view
        //设置宽高
        width = ViewGroup.LayoutParams.MATCH_PARENT
        val manager = cx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        manager.defaultDisplay.getSize(point)


        val windowHeight = point.y
        height = windowHeight * 3 / 5

        // 设置可以获取焦点
        isFocusable = true
        // 设置外部点击
        isOutsideTouchable=true

        // api25低版本 - 设置背景图,能够响应返回按钮
        setBackgroundDrawable(ColorDrawable())


    }
}