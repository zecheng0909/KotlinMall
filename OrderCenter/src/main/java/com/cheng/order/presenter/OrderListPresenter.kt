package com.cheng.order.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.order.presenter.view.OrderListView
import com.cheng.order.service.OrderService
import com.kotlin.order.data.protocol.OrderInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-04
 * Time: 20:16
 * Describe:
 */

class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService

    /**
     * 根据订单状态查询查询订单列表
     */
    fun getOrderList(orderStatus: Int) {
        if (!checkNerWork()) {
            return
        }
        orderService.getOrderList(orderStatus = orderStatus)
                .execute(object : BaseSubscriber<MutableList<OrderInfo>?>(mView) {
                    override fun onNext(t: MutableList<OrderInfo>?) {
                        super.onNext(t)
                        mView.onGetOrderListResult(t)
                    }
                }, lifecycleProvider)

    }
}