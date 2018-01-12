package com.cheng.goods.data.protocol

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 22:45
 * Describe: 获取商品一级/二级分类列表参数的数据类
 *
 * @parentId 传0代表获取的是一级分类,获取二级分类时传对应id
 */

data class GetCategoryRequest(val parentId: Int = 0)