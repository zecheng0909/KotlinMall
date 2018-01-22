package com.cheng.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.loadUrl
import com.cheng.baselibrary.ui.fragment.BaseFragment
import com.cheng.goods.R
import com.cheng.goods.event.GoodsDetailImageEvent
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * User: Cheng
 * Date: 2018-01-20
 * Time: 11:36
 * Describe:
 */

class GoodsDetailTabTwoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_two,null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
                .subscribe {
                    goodsDetailOneIv.loadUrl(it.imageOne)
                    goodsDetailTwoIv.loadUrl(it.imageTwo)
                }
                .registerInBus(this)
    }
}