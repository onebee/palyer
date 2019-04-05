package com.hand.player.ui.activity

import android.support.v7.widget.Toolbar
import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.util.FragmentUtil
import com.hand.player.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }


    override fun getLayoutId(): Int {
         return  R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {

        bottomBar.setOnTabSelectListener {

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,FragmentUtil.fragmentUtil.getFragment(it),it.toString()).commit()

        }
    }

}
