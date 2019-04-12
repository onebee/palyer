package com.hand.player.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.hand.player.model.AudioBean
import org.greenrobot.eventbus.EventBus

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class AudioService : Service() {

    var list: ArrayList<AudioBean>? = null
    var position: Int = 0
    //    val mediaPlayer by lazy { MediaPlayer() }
    var mediaPlayer: MediaPlayer? = null

    val binder by lazy { AudioBinder() }

    override fun onCreate() {
        super.onCreate()

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

    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener {
        override fun getProgress(): Int {

            return mediaPlayer?.currentPosition?:0
        }

        override fun getDuration(): Int {
            return mediaPlayer?.duration?:0

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
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }

        }
    }
}