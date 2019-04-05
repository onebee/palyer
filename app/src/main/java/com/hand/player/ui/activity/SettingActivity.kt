package com.hand.player.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.util.ToolBarManager

/**
 * @author  diaokaibin@gmail.com on 2019/4/4.
 */
class SettingActivity : BaseActivity(), ToolBarManager {

    override val toolbar: Toolbar by lazy {

        findViewById<Toolbar>(R.id.toolbar)
    }

    override fun getLayoutId(): Int {

        return R.layout.activity_setting
    }

    override fun initData() {
        super.initData()
        initSettingToolBar()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        println(sp.getBoolean("push",false))
    }

}