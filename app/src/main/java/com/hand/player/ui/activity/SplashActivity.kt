package com.hand.player.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.hand.player.R
import com.hand.player.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/4.
 */
class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun onAnimationEnd(view: View?) {

        startActivityAndFinish<MainActivity>()


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        super.initData()
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(1000).start()

    }
}