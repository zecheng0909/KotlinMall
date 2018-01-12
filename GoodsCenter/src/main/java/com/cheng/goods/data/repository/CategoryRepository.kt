package com.cheng.goods.data.repository

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.goods.data.api.GoodsApi
import com.cheng.goods.data.protocol.CategoryInfo
import com.cheng.goods.data.protocol.GetCategoryRequest
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 09:45
 * Describe: Category模块网络请求层
 */

class CategoryRepository @Inject constructor() {

    fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<CategoryInfo>?>> {
        return RetrofitFactory.retrofitFactory.create(GoodsApi::class.java)
                .getCategory(GetCategoryRequest(parentId))
    }
}