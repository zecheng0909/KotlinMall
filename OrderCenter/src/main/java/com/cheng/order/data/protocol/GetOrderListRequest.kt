package com.kotlin.order.data.protocol

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:00
 * Describe: 根据订单状态查询查询订单列表
 */

data class GetOrderListRequest(val orderStatus:Int)
