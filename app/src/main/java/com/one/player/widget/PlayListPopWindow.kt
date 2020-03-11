package com.one.player.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.one.player.R

/**
 * @author  diaokaibin@gmail.com on 2019/4/12.
 */
class PlayListPopWindow(cx: Context, adapter: BaseAdapter, listener: AdapterView.OnItemClickListener,val window: Window) : PopupWindow() {

    //记录当前应用程序窗体透明度
    var aplha:Float = 0f

    init {

        // 记录当前窗体透明度
       aplha = window.attributes.alpha

        val view = LayoutInflater.from(cx).inflate(R.layout.pop_playlist, null, false)


        val listView = view.findViewById<ListView>(R.id.listView)

        //适配
        listView.adapter = adapter

        listView.onItemClickListener = listener

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
        isOutsideTouchable = true

        // api25低版本 - 设置背景图,能够响应返回按钮
        setBackgroundDrawable(ColorDrawable())

        // 处理popwindow 动画
        animationStyle = R.style.pop




    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        // popwindow 已经显示
        val attributes = window.attributes
        attributes.alpha=0.5f
        window.attributes = attributes
    }

    override fun dismiss() {
        // popwindow 消失
        super.dismiss()
        val attributes = window.attributes
        attributes.alpha = this.aplha
        window.attributes = attributes


    }
}