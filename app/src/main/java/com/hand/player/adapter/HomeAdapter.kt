package com.hand.player.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hand.player.widget.HomeItemView

/**
 * @author  diaokaibin@gmail.com on 2019/4/5.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {


        return HomeHolder(HomeItemView(parent?.context))
    }

    override fun getItemCount(): Int {

        return 5
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
    }


    class HomeHolder(item: View) : RecyclerView.ViewHolder(item) {

    }
}