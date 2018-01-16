package com.kotlin.goods.data.protocol

/*
    获取商品详情请求
 */

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 16:45
 * Describe: 获取商品详情请求
 *
 * @goodsId 商品ID
 */

data class GetGoodsDetailRequest(val goodsId: Int)
