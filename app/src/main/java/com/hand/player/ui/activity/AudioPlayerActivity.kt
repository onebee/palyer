package com.hand.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.service.AudioService
import com.hand.player.service.IService
import kotlinx.android.synthetic.main.activity_music_player_bottom.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class AudioPlayerActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.state -> updatePlayState()

        }
    }

    private fun updatePlayState() {
        iService?.updatePlayState()

        updatePlayStateButton()

    }

    private fun updatePlayStateButton() {

        val isPlaying = iService?.isPlaying()
        isPlaying?.let {

            if (isPlaying) {
                state.setImageResource(R.drawable.selector_btn_audio_play)
            } else {
                state.setImageResource(R.drawable.selector_btn_audio_pause)

            }
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    val connection by lazy { AudioConnection() }

    override fun initData() {

//        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position", -1)

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


//        val intent = Intent(this, AudioService::class.java)
//        intent.putExtra("list", list)
//        intent.putExtra("position", position)

        val intent = intent
        intent.setClass(this, AudioService::class.java)
        startService(intent)

        bindService(intent, connection, Context.BIND_AUTO_CREATE)

    }


    override fun initListener() {
        //
        state.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    var iService: IService? = null

    inner class AudioConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = service as IService
        }

    }
}