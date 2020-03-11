package com.one.player.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.one.player.R
import com.one.player.base.BaseActivity
import com.one.player.util.ToolBarManager

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