package com.hand.player.util

import com.hand.player.model.LrcBean
import java.io.File

/**
 * @author  diaokaibin@gmail.com on 2019/4/15.
 */
object LyricUtil {

    /**
     * 解析歌词文件 获取歌词集合
     */
    fun parseLyric(file: File): List<LrcBean> {

        val list = ArrayList<LrcBean>()
        if (!file.exists()) {
            list.add(LrcBean(0, "歌词加载错误"))
        }
        val linesList = file.readLines()
        for (line in linesList) {
            //解析一行

            val lineList: List<LrcBean> = parseLine(line)
            //添加到集合中
            list.addAll(lineList)

        }

        return list

    }

    private fun parseLine(line: String): List<LrcBean> {
        val list = ArrayList<LrcBean>()
        val arr = line.split("]")
        val content = arr.get(arr.size - 1)
        for (index in 0 until arr.size - 1) {
            val startTime = parseTime(arr.get(index))
            list.add(LrcBean(startTime, content))

        }
        list.sortBy {
            it.startTime
        }
        return list
    }

    private fun parseTime(time: String): Int {

        val timeS = time.substring(1)

        val list = timeS.split(":")

        var hour = 0
        var min: Int
        var sec: Float

        if (list.size == 3) {

            // 有小时
            hour = list[0].toInt() * 60 * 60 * 1000
            min = list[1].toInt() * 60 * 1000
            sec = list[2].toFloat() * 1000
        } else {
            min = list[0].toInt() * 60 * 1000
            sec = list[1].toFloat() * 1000
        }
        return (hour + min + sec).toInt()

    }
}