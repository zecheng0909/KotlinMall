package com.kotlin.order.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.kotlin.order.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:00
 * Describe: 订单模块接口
 */

interface OrderApi {

    /**
     * 取消订单
     */
    @POST("order/cancel")
    fun cancelOrder(@Body req: CancelOrderRequest): Observable<BaseResponse<String>>

    /**
     * 确认订单
     */
    @POST("order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderRequest): Observable<BaseResponse<String>>

    /**
     * 根据ID获取订单
     */
    @POST("order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdRequest): Observable<BaseResponse<OrderInfo>>

    /**
     * 根据订单状态查询查询订单列表
     */
    @POST("order/getOrderList")
    fun getOrderList(@Body req: GetOrderListRequest): Observable<BaseResponse<MutableList<OrderInfo>?>>

    /**
     * 提交订单
     */
    @POST("order/submitOrder")
    fun submitOrder(@Body req: SubmitOrderRequest): Observable<BaseResponse<String>>

}
