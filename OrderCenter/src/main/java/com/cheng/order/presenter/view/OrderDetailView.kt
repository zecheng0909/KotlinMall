package com.cheng.order.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.OrderInfo

/**
 * User: Cheng
 * Date: 2018-02-05
 * Time: 13:07
 * Describe: 订单详情页回调接口
 */

interface OrderDetailView : BaseView {

    fun onGetOrderDetailResult(orderInfo: OrderInfo)
}