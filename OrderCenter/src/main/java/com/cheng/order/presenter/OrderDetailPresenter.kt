package com.cheng.order.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.order.presenter.view.OrderDetailView
import com.cheng.order.service.OrderService
import com.kotlin.order.data.protocol.OrderInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-05
 * Time: 13:07
 * Describe:
 */

class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

    @Inject
    lateinit var orderService: OrderService

    /**
     * 根据ID获取订单
     */
    fun getOrderById(orderId: Int) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId = orderId)
                .execute(object : BaseSubscriber<OrderInfo>(mView) {
                    override fun onNext(t: OrderInfo) {
                        super.onNext(t)
                        mView.onGetOrderDetailResult(t)
                    }
                }, lifecycleProvider)
    }

}