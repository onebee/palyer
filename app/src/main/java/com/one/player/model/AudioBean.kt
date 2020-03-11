package com.one.player.model

import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
data class AudioBean(var data: String, var size: Long, var display_name: String, var artist: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeLong(size)
        parcel.writeString(display_name)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioBean> {
        override fun createFromParcel(parcel: Parcel): AudioBean {
            return AudioBean(parcel)
        }

        override fun newArray(size: Int): Array<AudioBean?> {
            return arrayOfNulls(size)
        }


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

        fun getAudioBeans(cursor: Cursor?): ArrayList<AudioBean> {

            val list = ArrayList<AudioBean>()
            cursor?.let {

                it.moveToPosition(-1)

                // 解析cursor 添加到集合
                while (it.moveToNext()) {
                    val audioBean = getAudioBean(cursor)
                    list.add(audioBean)
                }


            }
            return list


        }
    }


}