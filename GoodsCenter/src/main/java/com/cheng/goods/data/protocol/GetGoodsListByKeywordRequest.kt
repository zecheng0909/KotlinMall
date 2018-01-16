package com.kotlin.goods.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 16:45
 * Describe: 按关键字搜索商品
 *
 * @keyword 搜索的关键字
 * @pageNo 页码
 */

data class GetGoodsListByKeywordRequest(
        val keyword: String,
        val pageNo: Int
)
