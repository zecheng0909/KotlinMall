package com.kotlin.order.data.protocol

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:17
 * Describe: 订单数据类
 */

data class OrderInfo(
        val id: Int,
        val payType: Int,
        var shipAddress: ShipAddressInfo?,
        val totalPrice: Long,
        var orderStatus: Int,
        val orderGoodsList: MutableList<OrderGoodsInfo>
)

