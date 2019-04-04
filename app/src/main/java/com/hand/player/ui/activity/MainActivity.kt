package com.hand.player.ui.activity

import android.os.Bundle
import com.hand.player.R
import com.hand.player.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
         return  R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
