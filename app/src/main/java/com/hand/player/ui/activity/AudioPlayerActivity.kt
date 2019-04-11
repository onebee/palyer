package com.hand.player.ui.activity

import com.hand.player.R
import com.hand.player.base.BaseActivity
import com.hand.player.model.AudioBean
import org.jetbrains.anko.info

/**
 * @author  diaokaibin@gmail.com on 2019/4/11.
 */
class AudioPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    override fun initData() {

        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
        val position = intent.getIntExtra("position",-1)

        info { list.toString() }
        info { position.toString() }
    }
}