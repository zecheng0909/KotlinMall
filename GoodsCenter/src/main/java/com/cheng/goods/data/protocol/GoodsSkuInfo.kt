package com.kotlin.goods.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 16:39
 * Describe: 商品SKU的数据类
 */

data class GoodsSkuInfo(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
)


