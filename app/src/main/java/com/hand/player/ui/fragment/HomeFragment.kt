package com.hand.player.ui.fragment

import android.view.View
import android.widget.TextView
import com.hand.player.base.BaseFragment

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeFragment : BaseFragment() {
    override fun initView(): View? {
        val tv = TextView(context)
        tv.setText(javaClass.simpleName)
        return tv

    }
}