package com.cheng.order.service

import com.kotlin.order.data.protocol.OrderInfo
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 11:29
 * Describe:
 */

interface OrderService {

    fun getOrderById(orderId: Int): Observable<OrderInfo>

    fun getOrderList(orderStatus: Int): Observable<MutableList<OrderInfo>?>

    fun submitOrder(order: OrderInfo): Observable<Boolean>

    fun cancelOrder(orderId: Int): Observable<Boolean>

    fun confirmOrder(orderId: Int): Observable<Boolean>
}