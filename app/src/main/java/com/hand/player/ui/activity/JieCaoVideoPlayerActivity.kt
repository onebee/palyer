package com.hand.player.ui.activity

import cn.jzvd.Jzvd
import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_jiecao_video_player.*
import org.jetbrains.anko.info


/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class JieCaoVideoPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_jiecao_video_player
    }

    override fun initData() {

        val data = intent.data
        info { data.toString() }
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