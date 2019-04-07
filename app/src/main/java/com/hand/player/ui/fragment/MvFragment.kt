package com.hand.player.ui.fragment

import android.view.View
import com.hand.player.R
import com.hand.player.adapter.MvPageAdapter
import com.hand.player.base.BaseFragment
import com.hand.player.model.MvAreaBean
import com.hand.player.presenter.impl.MvPresenterImpl
import com.hand.player.ui.view.MvView
import kotlinx.android.synthetic.main.fragment_mv.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class MvFragment : BaseFragment(), MvView {



    override fun onError(msg: String?) {

        myToash("加载区域数据失败")
    }

    override fun onSuccess(result: List<MvAreaBean>) {
        myToash("加载区域数据成功")
        val adapter = MvPageAdapter(context,result,childFragmentManager)

        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)


    }

    val presenter by lazy { MvPresenterImpl(this) }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_mv, null)
    }

    override fun initListener() {
        super.initListener()

    }

    override fun initData() {

        presenter.loadDates()
    }
}