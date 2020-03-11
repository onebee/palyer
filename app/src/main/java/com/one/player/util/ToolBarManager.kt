package com.one.player.util

import android.support.v7.widget.Toolbar
import com.one.player.R
import com.one.player.ui.activity.SettingActivity
import org.jetbrains.anko.startActivity

/**
 * @author  diaokaibin@gmail.com on 2019/4/4.
 */
interface ToolBarManager {
    val  toolbar:Toolbar

    fun initMainToolBar(){
        toolbar.setTitle("猎豹影音")
        toolbar.inflateMenu(R.menu.main)

        // 如果java 接口中只有一个未实现的方法,可以省略接口对象 直接用{} 表示未实现的方法
        toolbar.setOnMenuItemClickListener {
//            println("$it")
            toolbar.context.startActivity<SettingActivity>()
            true
        }
//        toolbar.setOnMenuItemClickListener(object :Toolbar.OnMenuItemClickListener{
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when (item?.itemId) {
//                    R.id.setting-> {
//
//
//                    }
//
//                }
//                return true
//            }
//
//
//        })
    }


    fun initSettingToolBar(){

        toolbar.setTitle("设置页面")


    }


}

