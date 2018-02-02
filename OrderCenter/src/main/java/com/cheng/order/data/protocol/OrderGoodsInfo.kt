package com.kotlin.order.data.protocol

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:17
 * Describe: 订单中的商品数据类
 */

data class OrderGoodsInfo(
        val id: Int,
        var goodsId: Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        val goodsCount: Int,
        val goodsSku: String,
        val orderId: Int
)

