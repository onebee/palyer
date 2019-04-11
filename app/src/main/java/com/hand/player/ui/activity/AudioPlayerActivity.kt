package com.hand.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.model.AudioBean
import com.hand.player.service.AudioService

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class AudioPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    val connection by lazy { AudioConnection() }

    override fun initData() {

        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
        val position = intent.getIntExtra("position",-1)

//        info { list.toString() }
//        info { position.toString() }

//        val mediaPlayer =  MediaPlayer()
//        mediaPlayer.setOnPreparedListener{
//
//            mediaPlayer.start()
//        }
//
//        mediaPlayer.setDataSource(list.get(position).data)
//        mediaPlayer.prepareAsync()


        val intent = Intent(this, AudioService::class.java)
        intent.putExtra("list",list)
        intent.putExtra("position",position)
        startService(intent)

        bindService(intent,connection,Context.BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    class AudioConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        }

    }
}