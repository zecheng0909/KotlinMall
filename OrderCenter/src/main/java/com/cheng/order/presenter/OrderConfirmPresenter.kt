package com.cheng.order.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.order.presenter.view.OrderConfirmView
import com.cheng.order.service.OrderService
import com.kotlin.order.data.protocol.OrderInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 11:34
 * Describe:
 */

class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

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
                        mView.onGetOrderByIdResult(t)
                    }
                }, lifecycleProvider)
    }

    /**
     * 提交订单
     */
    fun submitOrder(order: OrderInfo) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order = order)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.onSubmitOrderResult(t)
                    }
                }, lifecycleProvider)
    }

}