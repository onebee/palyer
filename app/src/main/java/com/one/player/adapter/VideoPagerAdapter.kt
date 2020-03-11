package com.one.player.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.one.player.ui.fragment.DefaultFragment

/**
 * @author  diaokaibin@gmail.com on 2019/4/9.
 */
class VideoPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return DefaultFragment()

    }

    override fun getCount(): Int {
        return 3
    }
}