package com.one.player.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.one.player.model.MvAreaBean
import com.one.player.ui.fragment.MvPagerFragment

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
        bundle.putString("args", list?.get(position).code)
        val fragment = Fragment.instantiate(context, MvPagerFragment::class.java.name, bundle)


        return fragment

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }

    override fun getCount(): Int {
        return list?.size ?: 0
    }
}