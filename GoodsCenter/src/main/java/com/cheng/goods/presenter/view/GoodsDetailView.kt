package com.cheng.goods.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.goods.data.protocol.GoodsInfo

/**
 * User: Cheng
 * Date: 2018-01-22
 * Time: 13:14
 * Describe: 商品详情页面的回调
 */

interface GoodsDetailView : BaseView {

    fun getGoodsDetailResult(result: GoodsInfo)


    fun addCartResult(result: Int)
}