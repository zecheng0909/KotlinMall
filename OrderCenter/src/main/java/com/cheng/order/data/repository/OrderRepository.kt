package com.kotlin.order.data.repository


import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.kotlin.order.data.api.OrderApi
import com.kotlin.order.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:20
 * Describe: 订单数据层
 */

class OrderRepository @Inject constructor() {

    /**
     * 取消订单
     */
    fun cancelOrder(orderId: Int): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory
                .create(OrderApi::class.java)
                .cancelOrder(CancelOrderRequest(orderId))
    }

    /**
     * 确认订单
     */
    fun confirmOrder(orderId: Int): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory
                .create(OrderApi::class.java)
                .confirmOrder(ConfirmOrderRequest(orderId))
    }

    /**
     * 根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<BaseResponse<OrderInfo>> {
        return RetrofitFactory.retrofitFactory
                .create(OrderApi::class.java)
                .getOrderById(GetOrderByIdRequest(orderId))
    }

    /**
     * 根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<BaseResponse<MutableList<OrderInfo>?>> {
        return RetrofitFactory.retrofitFactory
                .create(OrderApi::class.java)
                .getOrderList(GetOrderListRequest(orderStatus))
    }

    /**
     * 提交订单
     */
    fun submitOrder(order: OrderInfo): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory
                .create(OrderApi::class.java)
                .submitOrder(SubmitOrderRequest(order))
    }

}
