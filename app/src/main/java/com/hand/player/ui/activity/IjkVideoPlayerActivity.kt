package com.hand.player.ui.activity

import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_ijk.*
import org.jetbrains.anko.info

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class IjkVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_ijk_video_player
    }

    override fun initData() {

        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        info { videoPlayBean.toString() }

        videoView.setVideoPath(videoPlayBean.url)

        videoView.setOnPreparedListener {

            videoView.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback()

    }
}