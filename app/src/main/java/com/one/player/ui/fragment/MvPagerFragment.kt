package com.one.player.ui.fragment

import com.one.player.adapter.MvListAdapter
import com.one.player.base.BaseListAdapter
import com.one.player.base.BaseListFragment
import com.one.player.base.BaseListPresenter
import com.one.player.model.MvPagerBean
import com.one.player.model.VideoPlayBean
import com.one.player.model.VideosBean
import com.one.player.presenter.impl.MvListPresenterImpl
import com.one.player.ui.activity.JieCaoVideoPlayerActivity
import com.one.player.ui.view.MvListView
import com.one.player.widget.MvItemView
import org.jetbrains.anko.support.v4.startActivity

/**
 * @author  diaokaibin@gmail.com on 2019/4/7.
 */
class MvPagerFragment : BaseListFragment<MvPagerBean, VideosBean, MvItemView>(), MvListView {

    var code:String? = null

    override fun init() {

        code = arguments?.getString("args")
    }

    override fun getSpecialAdapter(): BaseListAdapter<VideosBean, MvItemView> {

        return MvListAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {

        return MvListPresenterImpl(code!!,this)
    }

    override fun getList(response: MvPagerBean?): List<VideosBean>? {
        return response?.videos
    }

    override fun initListener() {
        super.initListener()
        // 设置条目点击事件监听
        adapter.setMyLisenter {
//            myToash(it.toString())
            //todo player
            val videoPlayBean = VideoPlayBean(it.id,it.title,it.url)
            startActivity<JieCaoVideoPlayerActivity>("item" to videoPlayBean)
        }
    }
}