package com.hand.player.service

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
interface IService {
     fun getDuration(): Int
    fun updatePlayState()
    fun isPlaying(): Boolean?
    fun getProgress(): Int
}