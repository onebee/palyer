package com.hand.player.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.hand.player.model.AudioBean
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class AudioService : Service() {

    var list: ArrayList<AudioBean>? = null
    var position: Int = 0
    //    val mediaPlayer by lazy { MediaPlayer() }
    var mediaPlayer: MediaPlayer? = null

    val sp by lazy {   getSharedPreferences("config", Context.MODE_PRIVATE)}

    companion object {
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3

    }


    var mode = MODE_ALL

    val binder by lazy { AudioBinder() }

    override fun onCreate() {
        super.onCreate()

        // 获取播放模式
      mode =   sp.getInt("mode", MODE_ALL)

    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        list = intent?.getParcelableArrayListExtra<AudioBean>("list")
        position = intent?.getIntExtra("position", -1) ?: -1

        binder.playItem()

        return START_NOT_STICKY

    }

    override fun onBind(intent: Intent?): IBinder? {

        return binder

    }

    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

        override fun getPlayMode(): Int {
            return mode
        }

        /***
         * 修改播放模式
         */
        override fun updatePlayMode() {

            when (mode) {
                MODE_ALL -> mode = MODE_SINGLE
                MODE_SINGLE -> mode = MODE_RANDOM
                MODE_RANDOM -> mode = MODE_ALL
            }
            sp.edit().putInt("mode",mode).apply()

        }


        override fun onCompletion(mp: MediaPlayer?) {

            antoPlayNext()

        }

        /***
         * 根据播放模式 自动播放下一曲
         */
        private fun antoPlayNext() {

            when (mode) {
                MODE_ALL -> {
                    list?.let {
                        position = (position + 1) % it.size
                    }

                }
//                MODE_SINGLE ->
                MODE_RANDOM -> {

                    list?.let {

                        position = Random().nextInt(it.size)
                    }
                }
            }

            playItem()


        }

        /***
         * 跳转到当前进度 进行播放
         */
        override fun seekTo(progress: Int) {

            mediaPlayer?.seekTo(progress)

        }

        override fun getProgress(): Int {

            return mediaPlayer?.currentPosition ?: 0
        }

        override fun getDuration(): Int {
            return mediaPlayer?.duration ?: 0

        }

        override fun updatePlayState() {
            val isPlaying = isPlaying()
            isPlaying?.let {

                if (isPlaying) {

                    mediaPlayer?.pause()
                } else {
                    mediaPlayer?.start()
                }

            }


        }

        override fun isPlaying(): Boolean? {
            return mediaPlayer?.isPlaying
        }

        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer?.start()
            // notice ui
            notifyUpdateUi()

        }

        private fun notifyUpdateUi() {

            EventBus.getDefault().post(list?.get(position))


        }

        fun playItem() {
            mediaPlayer = MediaPlayer()

            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setOnCompletionListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }

        }
    }
}