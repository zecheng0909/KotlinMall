package com.kotlin.goods.data.protocol

/*
    商品SKU数据类
 */
data class GoodsSkuInfo(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
        )


