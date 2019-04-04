package com.hand.player.ui.activity

import android.support.v7.widget.Toolbar
import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.util.ToolBarManager
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }


    override fun getLayoutId(): Int {
         return  R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

}
