package com.cheng.goods.service

import com.cheng.goods.data.protocol.CategoryInfo
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 09:48
 * Describe:
 */

interface CategoryService {

    fun getCategory(parentId: Int): Observable<MutableList<CategoryInfo>?>

}