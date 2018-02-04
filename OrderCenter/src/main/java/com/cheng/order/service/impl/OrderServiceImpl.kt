package com.cheng.order.service.impl

import com.cheng.baselibrary.ext.convert
import com.cheng.baselibrary.ext.convertBoolean
import com.cheng.order.service.OrderService
import com.kotlin.order.data.protocol.OrderInfo
import com.kotlin.order.data.repository.OrderRepository
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 11:31
 * Describe:
 */

class OrderServiceImpl @Inject constructor() : OrderService {

    @Inject
    lateinit var orderRepository: OrderRepository

    override fun getOrderById(orderId: Int): Observable<OrderInfo> {
        return orderRepository.getOrderById(orderId = orderId).convert()
    }

    override fun getOrderList(orderStatus: Int): Observable<MutableList<OrderInfo>?> {
        return orderRepository.getOrderList(orderStatus = orderStatus).convert()
    }

    override fun submitOrder(order: OrderInfo): Observable<Boolean> {
        return orderRepository.submitOrder(order = order).convertBoolean()
    }
}