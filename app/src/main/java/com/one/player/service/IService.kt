package com.one.player.service

import com.one.player.model.AudioBean

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
interface IService {
     fun getDuration(): Int
    fun updatePlayState()
    fun isPlaying(): Boolean?
    fun getProgress(): Int
    fun seekTo(progress: Int)
    fun updatePlayMode()
    fun getPlayMode(): Int
    fun playPre()
    fun playNext()
    fun getPlayList(): List<AudioBean>?
    fun playPosition(position: Int)
}