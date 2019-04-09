package com.hand.player.ui.activity

import android.support.v4.view.ViewPager
import cn.jzvd.Jzvd
import com.hand.player.R
import com.hand.player.adapter.VideoPagerAdapter
import com.hand.player.base.BaseActivity
import com.hand.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_jiecao_video_player.*


/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class JieCaoVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_jiecao_video_player
    }

    override fun initData() {

        val data = intent.data
        if (data == null) {
            val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")

            videoplayer.setUp(
                videoPlayBean.url,
                videoPlayBean.title,
                Jzvd.SCREEN_NORMAL
            )
        } else {
            if (data.toString().startsWith("http")) {

                videoplayer.setUp(
                    data.toString(),
                    data.toString(),
                    Jzvd.SCREEN_NORMAL
                )
            } else {
                videoplayer.setUp(
                    data.path.toString(),
                    data.path.toString(),
                    Jzvd.SCREEN_NORMAL
                )
            }

        }


    }

    override fun initListener() {

        viewPager.adapter = VideoPagerAdapter(supportFragmentManager)

        rg.setOnCheckedChangeListener { _, i ->

            when (i) {
                R.id.rb1 -> viewPager.currentItem = 0
                R.id.rb2 -> viewPager.currentItem = 1
                R.id.rb3 -> viewPager.currentItem = 2

            }

        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0-> rg.check(R.id.rb1)
                    1-> rg.check(R.id.rb2)
                    2-> rg.check(R.id.rb3)
                }
            }

        })


    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
//        videoplayer.releaseAllVideos()
    }
}