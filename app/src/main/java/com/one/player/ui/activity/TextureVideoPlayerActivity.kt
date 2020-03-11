package com.one.player.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import com.one.player.R
import com.one.player.base.BaseActivity
import com.one.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_text_player.*
import org.jetbrains.anko.info

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class TextureVideoPlayerActivity : BaseActivity(), TextureView.SurfaceTextureListener {

      val mediaPlayer by lazy { MediaPlayer() }

    var videoPlayBean:VideoPlayBean? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_video_text_player
    }

    override fun initData() {

        videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        info { videoPlayBean.toString() }

       texture_video.surfaceTextureListener = this
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        mediaPlayer?.let {

            mediaPlayer.stop()
            mediaPlayer.release()
        }

        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
       videoPlayBean?.let {


           mediaPlayer.setDataSource(it.url)
           mediaPlayer.setSurface(Surface(surface)) // 设置播放视频画面
           mediaPlayer.prepareAsync()
           mediaPlayer.setOnPreparedListener{
               mediaPlayer.start()

               // 旋转画面
               texture_video.rotation=90f
           }
       }

    }
}