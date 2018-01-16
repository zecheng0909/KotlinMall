package com.kotlin.goods.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.goods.data.protocol.CategoryInfo
import com.cheng.goods.data.protocol.GetCategoryRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 22:42
 * Describe: Goods分类模块,访问网络需要的api
 */

interface CategoryApi {

    /**
     * 获取商品列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body getCategoryRequest: GetCategoryRequest):
            Observable<BaseResponse<MutableList<CategoryInfo>?>>
}
