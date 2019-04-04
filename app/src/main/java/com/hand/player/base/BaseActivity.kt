package com.hand.player.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author  diaokaibin@gmail.com on 2019/4/3.
 */
abstract class BaseActivity : AppCompatActivity(),AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
        info { "哈哈" }
    }

   open protected fun initData() {


    }

   open protected fun initListener(){}


    abstract fun getLayoutId(): Int


   open protected fun myToash(msg: String) {
        runOnUiThread {
            toast(msg)
        }
    }


    inline fun <reified T: BaseActivity>startActivityAndFinish() {
        startActivity<T>()
        finish()
    }
}