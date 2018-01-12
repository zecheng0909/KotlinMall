package com.cheng.goods.service.impl

import com.cheng.baselibrary.ext.convert
import com.cheng.goods.data.protocol.CategoryInfo
import com.cheng.goods.data.repository.CategoryRepository
import com.cheng.goods.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 09:51
 * Describe:
 */

class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<CategoryInfo>?> {
        return categoryRepository.getCategory(parentId = parentId)
                .convert()
    }

}