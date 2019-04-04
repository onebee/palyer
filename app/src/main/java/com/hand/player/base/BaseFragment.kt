package com.hand.player.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.toast

/**
 * @author  diaokaibin@gmail.com on 2019/4/3.
 */
abstract class BaseFragment : Fragment(),AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        debug { "呵呵" }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

   open protected fun initData() {


    }

   open protected fun initListener() {


    }

    abstract fun initView(): View?

   open protected fun init() {


    }

  open  protected fun myToash(msg: String) {
        runOnUiThread {
            toast(msg)
        }
    }
}