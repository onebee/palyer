package com.one.player.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews
import com.one.player.R
import com.one.player.model.AudioBean
import com.one.player.ui.activity.AudioPlayerActivity
import com.one.player.ui.activity.MainActivity
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class AudioService : Service() {

    var list: ArrayList<AudioBean>? = null
    var position: Int = -2  // 当前正在播放的pos
    //    val mediaPlayer by lazy { MediaPlayer() }
    var mediaPlayer: MediaPlayer? = null

    var notification :Notification? = null
    var manager: NotificationManager? = null
    val sp by lazy { getSharedPreferences("config", Context.MODE_PRIVATE) }

    companion object {
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3

    }

    val FROM_PRE = 1
    val FROM_NEXT = 2
    val FROM_STATE = 3
    val FROM_CONTENT = 4

    var mode = MODE_ALL

    val binder by lazy { AudioBinder() }

    override fun onCreate() {
        super.onCreate()

        // 获取播放模式
        mode = sp.getInt("mode", MODE_ALL)

    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 判断进入service 方法
        val from = intent?.getIntExtra("from", -1)

        when (from) {

            FROM_PRE -> {
                binder.playPre()
            }
            FROM_NEXT -> {
                binder.playNext()
            }
            FROM_CONTENT -> {
                binder.notifyUpdateUi()

            }
            FROM_STATE -> {
                binder.updatePlayState()

            }
            else -> {
                list = intent?.getParcelableArrayListExtra<AudioBean>("list")
                val pos = intent?.getIntExtra("position", -1) ?: -1
                if (pos != position) {
                    position = pos
                    binder.playItem()

                } else {
                    binder.notifyUpdateUi()
                }

            }

        }


        return START_NOT_STICKY

    }

    override fun onBind(intent: Intent?): IBinder? {

        return binder

    }

    inner class AudioBinder : Binder(), IService, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

        /**
         * 播放当前位置的歌曲
         */
        override fun playPosition(position: Int) {
            this@AudioService.position = position
            playItem()

        }

        override fun getPlayList(): List<AudioBean>? {
            return list
        }


        override fun playPre() {
            // 获取要播放歌曲的position
            list?.let {
                when (mode) {

                    MODE_RANDOM -> list?.let {
                        position = Random().nextInt(it.size - 1)
                    }
                    else -> {
                        if (position == 0) position = it.size - 1
                        else position--
                    }

                }
            }

            playItem()

        }

        override fun playNext() {
            list?.let {
                when (mode) {

                    MODE_RANDOM -> Random().nextInt(it.size - 1)
                    else -> position = (position + 1) % it.size
                }
            }
            playItem()
        }

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
            sp.edit().putInt("mode", mode).apply()

        }


        override fun onCompletion(mp: MediaPlayer?) {

            autoPlayNext()

        }

        /***
         * 根据播放模式 自动播放下一曲
         */
        private fun autoPlayNext() {

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
                    pause()
                } else {
                    start()
                }
            }
        }

        private fun pause() {
            mediaPlayer?.pause()
            EventBus.getDefault().post(list?.get(position))
            // 更新通知栏的图标
            notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_pause_normal)

            // 重新显示
            manager?.notify(1,notification)

        }

        private fun start() {
            mediaPlayer?.start()
            EventBus.getDefault().post(list?.get(position))
            notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_play_normal)
            manager?.notify(1,notification)

        }


        override fun isPlaying(): Boolean? {
            return mediaPlayer?.isPlaying
        }

        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer?.start()
            // notice ui
            notifyUpdateUi()

            // 显示通知
            showNotification()


        }


        private fun showNotification() {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification = getNotification()
            manager?.notify(1, notification)

        }

        private fun getNotification(): Notification? {

            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            val notification = NotificationCompat.Builder(this@AudioService)
                .setTicker("正在播放" + list?.get(position)?.display_name)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setCustomContentView(getRemoteViews())
                .setWhen(System.currentTimeMillis())
                .setOngoing(true)// 设置不能滑动删除通知
                .setContentIntent(getPendingIntent()) // 通知栏主体点击事件
                .build()

            return notification

        }


        /**
         * 创建通知自定义view
         */
        private fun getRemoteViews(): RemoteViews? {
            val remoteViews = RemoteViews(packageName, R.layout.notification)
            //修改标题和内容
            remoteViews.setTextViewText(R.id.title, list?.get(position)?.display_name)
            remoteViews.setTextViewText(R.id.artist, list?.get(position)?.artist)
            //处理上一曲 下一曲  状态点击事件
            remoteViews.setOnClickPendingIntent(R.id.pre, getPrePendingIntent())
            remoteViews.setOnClickPendingIntent(R.id.state, getStatePendingIntent())
            remoteViews.setOnClickPendingIntent(R.id.next, getNextPendingIntent())
            return remoteViews
        }

        /**
         * 下一曲点击事件
         */
        private fun getNextPendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService, AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from", FROM_NEXT)
            val pendingIntent =
                PendingIntent.getService(this@AudioService, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 播放暂停按钮点击事件
         */
        private fun getStatePendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService, AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from", FROM_STATE)
            val pendingIntent =
                PendingIntent.getService(this@AudioService, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 上一曲点击事件
         */
        private fun getPrePendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService, AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from", FROM_PRE)
            val pendingIntent =
                PendingIntent.getService(this@AudioService, 4, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 通知栏主体点击事件
         */
        private fun getPendingIntent(): PendingIntent? {
            val intentM = Intent(this@AudioService, MainActivity::class.java)//点击主体进入当前界面中
            val intentA = Intent(this@AudioService, AudioPlayerActivity::class.java)//点击主体进入当前界面中
            intentA.putExtra("from", FROM_CONTENT)
            val intents = arrayOf(intentM, intentA)
            val pendingIntent =
                PendingIntent.getActivities(this@AudioService, 1, intents, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        fun notifyUpdateUi() {

            EventBus.getDefault().post(list?.get(position))

            Log.e("------", "size" + list?.size + "  pos :" + position)

        }

        fun playItem() {
            // 如果mediaPlayer 存在 先释放
            if (mediaPlayer != null) {
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
            mediaPlayer = MediaPlayer()

            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setOnCompletionListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }


            //更新通知栏播放icon 状态
            notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_play_normal)
            manager?.notify(1,notification)
        }
    }
}