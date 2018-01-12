package com.cheng.goods.data.protocol

import com.cheng.baselibrary.ext.PoKo

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 22:49
 * Describe: 商品分类的数据类
 *
 * @isSelected 代表了此分类的选中状态
 */

data class CategoryInfo(val id: Int,
                        val categoryName: String,
                        val categoryIcon: String = "",
                        val parentId: Int,
                        var isSelected: Boolean)