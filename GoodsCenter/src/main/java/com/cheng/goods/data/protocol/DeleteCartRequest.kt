package com.kotlin.goods.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-25
 * Time: 15:12
 * Describe: 删除购物车商品请求
 */

data class DeleteCartRequest(val cartIdList: List<Int> = arrayListOf())
