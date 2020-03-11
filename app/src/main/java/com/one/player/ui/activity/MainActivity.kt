package com.one.player.ui.activity

import android.support.v7.widget.Toolbar
import com.one.player.R
import com.one.player.base.BaseActivity
import com.one.player.util.FragmentUtil
import com.one.player.util.ToolBarManager
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
