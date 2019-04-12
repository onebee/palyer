package com.hand.player.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.SeekBar
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
class AudioPlayerActivity : BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener {


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

            R.id.mode -> updatePlayMode()

            R.id.pre -> iService?.playPre()

            R.id.next -> iService?.playNext()

        }
    }


    /**
     * 更新播放模式
     */
    private fun updatePlayMode() {
        // 修改service 中的mode
        iService?.updatePlayMode()
        updatePlayModeBtn()


    }

    /**
     * 根据播放模式修改图标
     */
    private fun updatePlayModeBtn() {
        iService?.let {
            val mode1: Int = it.getPlayMode()

            when (mode1) {
                AudioService.MODE_ALL -> mode.setImageResource(R.drawable.selector_btn_playmode_order)


                AudioService.MODE_SINGLE -> mode.setImageResource(R.drawable.selector_btn_playmode_single)


                AudioService.MODE_RANDOM -> mode.setImageResource(R.drawable.selector_btn_playmode_random)

            }
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

        progress_sk.max = duration

        //更新播放进度
        startUpdateProgress()

        //更新播放模式图标
        updatePlayModeBtn()


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
        progress.text = StringUtil.parseDuration(pro) + "/" + StringUtil.parseDuration(duration)
        //更新进度条
        progress_sk.setProgress(pro)

    }

    private fun updatePlayStateButton() {
        val isPlaying = iService?.isPlaying()
        isPlaying?.let {

            if (isPlaying) {
                state.setImageResource(R.drawable.selector_btn_audio_play)
                drawable?.start()
                // 开始更新进度
                handle.sendEmptyMessageDelayed(MSG_PROGRESS, 1000)
            } else {
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
                //停止更新进度
                handle.removeMessages(MSG_PROGRESS)
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

        progress_sk.setOnSeekBarChangeListener(this)

        mode.setOnClickListener(this)

        pre.setOnClickListener(this)
        next.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        EventBus.getDefault().unregister(this)

        // 清空handler 发送的所有消息
        handle.removeCallbacksAndMessages(null)

    }

    var iService: IService? = null

    inner class AudioConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = service as IService
        }

    }


    /***
     * fromUser 是否用户触摸
     */
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        if (!fromUser) return
        iService?.seekTo(progress)
        updateProgress(progress)


    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}