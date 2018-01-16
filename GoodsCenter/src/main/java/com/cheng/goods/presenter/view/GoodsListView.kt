package com.cheng.goods.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.goods.data.protocol.GoodsInfo

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 17:34
 * Describe: 商品列表页面的回调
 */

interface GoodsListView : BaseView {

    fun getGoodsListResult(result: MutableList<GoodsInfo>?)
}