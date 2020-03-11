package com.one.player

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        println("app  start hhh ")

    }
}