package com.cheng.goods.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.goods.data.protocol.CategoryInfo
import com.cheng.goods.presenter.view.CategoryView
import com.cheng.goods.service.CategoryService
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 09:55
 * Describe: 分类页面的逻辑层
 */

class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    /**
     * 获取分类列表
     */
    fun getCategory(parentId: Int) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId = parentId)
                .execute(object : BaseSubscriber<MutableList<CategoryInfo>?>(mView) {
                    override fun onNext(result: MutableList<CategoryInfo>?) {
                        mView.onGetCategoryResult(result)
                    }
                }, lifecycleProvider)
    }


}