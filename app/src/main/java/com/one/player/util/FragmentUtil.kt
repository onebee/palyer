package com.one.player.util

import com.one.player.R
import com.one.player.base.BaseFragment
import com.one.player.ui.fragment.HomeFragment
import com.one.player.ui.fragment.MvFragment
import com.one.player.ui.fragment.VBangFragment
import com.one.player.ui.fragment.YueDanFragment

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class FragmentUtil private  constructor(){

    val homeFragment by lazy { HomeFragment() }
    val myFragment by lazy { MvFragment() }
    val vbangFragment by lazy {VBangFragment()  }
    val yundanFragment by lazy { YueDanFragment() }

    companion object {
        val  fragmentUtil by lazy { FragmentUtil()}
    }

    fun getFragment(tagId:Int):BaseFragment?{
        when (tagId) {
            R.id.tab_home-> return  homeFragment
            R.id.tab_mv-> return  myFragment
            R.id.tab_vbang-> return  vbangFragment
            R.id.tab_yuedan-> return  yundanFragment
        }

        return null
    }

}