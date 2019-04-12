package com.hand.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.model.AudioBean
import com.hand.player.service.AudioService
import com.hand.player.service.IService
import com.hand.player.util.StringUtil
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.info

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class AudioPlayerActivity : BaseActivity(), View.OnClickListener {

    // 记录播放歌曲
    var audioBean: AudioBean? = null

    var duration: Int = 0

    var drawable: AnimationDrawable? = null

    val handle = object : Handler() {

        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MSG_PROGRESS -> startUpdateProgress()
            }

        }
    }

    val MSG_PROGRESS = 0


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.state -> updatePlayState()

        }
    }

    private fun updatePlayState() {
        iService?.updatePlayState()

        updatePlayStateButton()

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMAIN(itemBean: AudioBean) {
        info { "receive : " + itemBean.toString() }
        this.audioBean = itemBean

        audio_title.text = itemBean.display_name
        artist.text = itemBean.artist

        updatePlayStateButton()
        drawable = audio_anim.drawable as AnimationDrawable
        drawable?.start()

        // update play process
        duration = iService?.getDuration() ?: 0
        startUpdateProgress()


    }

    /***
     * 开始更新进度
     */
    private fun startUpdateProgress() {
        // 获取当前进度
        val progress: Int = iService?.getProgress() ?: 0
        //更新进度数据
        updateProgress(progress)
        // 定时获取进度

        handle.sendEmptyMessageDelayed(MSG_PROGRESS, 1000)


    }

    private fun updateProgress(pro: Int) {
      progress.text =   StringUtil.parseDuration(pro) + "/" + StringUtil.parseDuration(duration)



    }

    private fun updatePlayStateButton() {
        val isPlaying = iService?.isPlaying()
        isPlaying?.let {

            if (isPlaying) {
                state.setImageResource(R.drawable.selector_btn_audio_play)
                drawable?.start()
            } else {
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    val connection by lazy { AudioConnection() }

    override fun initData() {


        EventBus.getDefault().register(this)
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
        back.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        EventBus.getDefault().unregister(this)
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