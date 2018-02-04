package com.cheng.order.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.OrderInfo

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 11:35
 * Describe: 订单确认页的回调
 */

interface OrderConfirmView : BaseView {

    fun onGetOrderByIdResult(result: OrderInfo)

    fun onSubmitOrderResult(result: Boolean)

}