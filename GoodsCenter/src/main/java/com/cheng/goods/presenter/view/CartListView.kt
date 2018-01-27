package com.cheng.goods.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.goods.data.protocol.CartGoodsInfo

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 09:56
 * Describe: 分类页面的回调
 */

interface CartListView : BaseView {

    fun onGetCartListResult(result: MutableList<CartGoodsInfo>?)
}