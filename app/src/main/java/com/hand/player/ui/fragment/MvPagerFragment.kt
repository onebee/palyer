package com.hand.player.ui.fragment

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.hand.player.base.BaseFragment

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvPagerFragment : BaseFragment() {


    var name:String? = null

    override fun initView(): View? {
        val tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.text = javaClass.simpleName + name
        return tv
    }

    override fun init() {

         name = arguments?.getString("args")


    }

    override fun initData() {


    }
}