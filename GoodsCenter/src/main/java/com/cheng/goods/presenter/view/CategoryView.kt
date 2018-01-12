package com.cheng.goods.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.cheng.goods.data.protocol.CategoryInfo

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 09:56
 * Describe: 分类页面的回调
 */

interface CategoryView : BaseView {

    fun onGetCategoryResult(result: MutableList<CategoryInfo>?)
}