package com.hand.player.ui.fragment

import android.Manifest
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.AsyncTask
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import com.hand.player.R
import com.hand.player.adapter.VbangAdapter
import com.hand.player.base.BaseFragment
import com.hand.player.util.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class VBangFragment : BaseFragment() {


//    val handle = object : Handler() {
//
//        override fun handleMessage(msg: Message?) {
//
//            msg?.let {
//                val cursor: Cursor = msg.obj as Cursor
//                CursorUtil.logCursor(cursor)
//
//            }
//
//
//        }
//
//    }

    override fun initView(): View? {

        return View.inflate(context, R.layout.fragment_vbang, null)

    }

    override fun initData() {


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

//        AudioTask().execute(resolver)
        handlePermission()
//        loadData()

    }

    private fun handlePermission() {

        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val checkSelfPermission = ActivityCompat.checkSelfPermission(this.context!!, permission)
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            loadData()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity!!, permission)) {

                // 需要弹出
                alert("我们只会访问音乐文件", "温馨提示") {
                    yesButton { myRequestPermission() }
                    noButton { }
                }.show()

            } else {

                myRequestPermission()
            }
        }


    }

    private fun myRequestPermission() {
        val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

        requestPermissions(permission, 1)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadData()
            }
        }

    }

    private fun loadData() {
        val resolver = context?.contentResolver
        val handler = object : AsyncQueryHandler(resolver) {

            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {

                CursorUtil.logCursor(cursor)

                (cookie as VbangAdapter).swapCursor(cursor)


            }

        }

        handler.startQuery(
            0, adapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST
            ), null, null, null
        )
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


    var adapter: VbangAdapter? = null

    override fun initListener() {
        adapter = VbangAdapter(context, null)

        listView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.changeCursor(null)

    }
}