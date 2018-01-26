package com.kotlin.goods.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-25
 * Time: 14:54
 * Describe: 购物车提交订单
 */

data class SubmitCartRequest(val goodsList: List<CartGoodsInfo>, val totalPrice: Long)
