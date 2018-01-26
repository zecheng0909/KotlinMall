package com.kotlin.goods.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-25
 * Time: 14:54
 * Describe: 添加商品到购物车请求
 */

data class AddCartRequest(
        val goodsId: Int, //商品ID
        val goodsDesc: String, //商品描述
        val goodsIcon: String, //商品图标
        val goodsPrice: Long, //商品价格
        val goodsCount: Int, //商品数量
        val goodsSku: String //商品SKU
)
