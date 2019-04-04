package com.hand.player.util

import android.support.v7.widget.Toolbar

/**
 * @author  diaokaibin@gmail.com on 2019/4/4.
 */
interface ToolBarManager {
    val  toolbar:Toolbar

    fun initMainToolBar(){
        toolbar.setTitle("猎豹影音")
    }

}

