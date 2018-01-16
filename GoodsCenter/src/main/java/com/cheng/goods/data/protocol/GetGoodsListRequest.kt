package com.kotlin.goods.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 16:45
 * Describe: 按分类搜索商品
 *
 * @categoryId 分类ID
 * @pageNo 页码
 */

data class GetGoodsListRequest(val categoryId: Int,val pageNo: Int)
