package com.hand.player.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hand.player.R
import com.hand.player.adapter.HomeAdapter
import com.hand.player.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeFragment : BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        super.initListener()
        home_rv.layoutManager = LinearLayoutManager(context)

        val adapter = HomeAdapter()

        home_rv.adapter = adapter


    }
}