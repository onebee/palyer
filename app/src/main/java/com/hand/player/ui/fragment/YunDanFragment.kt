package com.hand.player.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hand.player.R
import com.hand.player.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class YunDanFragment : BaseFragment() {
    override fun initView(): View? {

        return View.inflate(context, R.layout.fragment_list, null)

    }

    override fun initListener() {
        recycleView.layoutManager = LinearLayoutManager(context)
    }
}