package com.one.player.util

import android.database.Cursor

/**
 * @author  diaokaibin@gmail.com on 2019/4/9.
 */
object CursorUtil {

    fun logCursor(cursor: Cursor?) {
        cursor?.let {

            it.moveToPosition(-1)
            while (it.moveToNext()) {
                for (index in 0 until it.columnCount) {// 包含不包含的关系
                    println("key=${it.getColumnName(index)} -- value = ${it.getString(index)}")
                }
            }
        }
    }
}