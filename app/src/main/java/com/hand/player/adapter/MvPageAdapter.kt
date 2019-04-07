package com.hand.player.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hand.player.ui.fragment.MvPagerFragment
import com.itheima.player.model.bean.MvAreaBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvPageAdapter(val context: Context?, var list: List<MvAreaBean>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
// 第一种数据传递方式
//        val fragment = MvPagerFragment()
//        val bundle = Bundle()

//        bundle.putString("args", list?.get(position).name)
//        fragment.arguments = bundle

        // 第二种数据传递方式
        val bundle = Bundle()
        bundle.putString("args", list?.get(position).name)
        val fragment = Fragment.instantiate(context, MvPagerFragment::class.java.name, bundle)


        return fragment

    }

    override fun getCount(): Int {
        return list?.size ?: 0
    }
}