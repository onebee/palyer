package com.hand.player.model

import android.database.Cursor
import android.provider.MediaStore

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
data class AudioBean(var data: String, var size: Long, var display_name: String, var artist: String) {
    companion object {

        /***
         * 根据特定位置上的 cursor 获取bean
         */
        fun getAudioBean(cursor: Cursor?): AudioBean {

            val audio = AudioBean("", 0, "", "")
            cursor?.let {

                audio.data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                audio.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audio.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                audio.display_name = audio.display_name.substring(0, audio.display_name.lastIndexOf("."))
                audio.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))


            }

            return audio

        }
    }


}