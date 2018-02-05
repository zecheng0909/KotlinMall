package com.cheng.order.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.OrderInfo

/**
 * User: Cheng
 * Date: 2018-02-04
 * Time: 20:19
 * Describe: 获取订单列表的回调接口
 */

interface OrderListView : BaseView {

    fun onGetOrderListResult(result: MutableList<OrderInfo>?)

    fun onConfirmOrderResult(result: Boolean)

    fun onCancelOrderResult(result: Boolean)
}