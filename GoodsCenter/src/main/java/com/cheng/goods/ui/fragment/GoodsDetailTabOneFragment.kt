package com.cheng.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ui.fragment.BaseFragment
import com.cheng.goods.R

/**
 * User: Cheng
 * Date: 2018-01-20
 * Time: 11:31
 * Describe:
 */

class GoodsDetailTabOneFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, null)
    }
}