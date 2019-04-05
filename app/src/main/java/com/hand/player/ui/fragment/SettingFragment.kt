package com.hand.player.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hand.player.R
import com.hand.player.ui.activity.AboutActivity
import org.jetbrains.anko.toast

/**
 * @author  diaokaibin@gmail.com on 2019/4/4.
 */
class SettingFragment:PreferenceFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if ("about".equals(key)) {
            toast("setting page ! ")
            startActivity(Intent(activity,AboutActivity::class.java))
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference)

    }
}