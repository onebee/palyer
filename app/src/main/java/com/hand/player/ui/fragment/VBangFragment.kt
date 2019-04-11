package com.hand.player.ui.fragment

import android.content.ContentResolver
import android.database.Cursor
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.view.View
import com.hand.player.R
import com.hand.player.base.BaseFragment
import com.hand.player.util.CursorUtil

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class VBangFragment : BaseFragment() {


    val handle = object : Handler() {

        override fun handleMessage(msg: Message?) {

            msg?.let {
                val cursor: Cursor = msg.obj as Cursor
                CursorUtil.logCursor(cursor)

            }


        }

    }

    override fun initView(): View? {

        return View.inflate(context, R.layout.fragment_vbang, null)

    }

    override fun initData() {

        val resolver = context?.contentResolver

//        val cursor = resolver?.query(
//            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//            arrayOf(
//                MediaStore.Audio.Media.DATA,
//                MediaStore.Audio.Media.SIZE,
//                MediaStore.Audio.Media.DISPLAY_NAME,
//                MediaStore.Audio.Media.ARTIST
//            ), null, null, null
//        )
//        CursorUtil.logCursor(cursor)

//        Thread(Runnable {
//
//            val cursor = resolver?.query(
//                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                arrayOf(
//                    MediaStore.Audio.Media.DATA,
//                    MediaStore.Audio.Media.SIZE,
//                    MediaStore.Audio.Media.DISPLAY_NAME,
//                    MediaStore.Audio.Media.ARTIST
//                ), null, null, null
//            )
//
//            val msg = Message.obtain()
//            msg.obj = cursor
//            handle.sendMessage(msg)
//        }).start()

        AudioTask().execute(resolver)

    }

    class AudioTask : AsyncTask<ContentResolver, Void, Cursor>() {

        override fun onPreExecute() {
            super.onPreExecute()

        }
        override fun doInBackground(vararg params: ContentResolver?): Cursor? {

            return params[0]?.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.SIZE,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.ARTIST
                ), null, null, null
            )
        }

        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)

            CursorUtil.logCursor(result)

        }



    }
}