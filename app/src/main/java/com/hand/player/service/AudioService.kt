package com.hand.player.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.hand.player.model.AudioBean

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

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(intent: Intent?): IBinder? {

        return binder

    }

    inner class AudioBinder : Binder(),IService, MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer?.start()

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