package com.hand.player.ui.fragment

import android.view.View
import android.widget.TextView
import com.hand.player.base.BaseFragment

/**
 * @author  diaokaibin@gmail.com on 2019/4/9.
 */
class DefaultFragment : BaseFragment() {
    override fun initView(): View? {
        val tv = TextView(context)
        tv.text = javaClass.simpleName
        return tv
    }
}