package com.kotlin.goods.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.kotlin.goods.data.protocol.AddCartRequest
import com.kotlin.goods.data.protocol.CartGoodsInfo
import com.kotlin.goods.data.protocol.DeleteCartRequest
import com.kotlin.goods.data.protocol.SubmitCartRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-25
 * Time: 14:42
 * Describe: 购物车分类模块接口
 */

interface CartApi {
    /**
     * 获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResponse<MutableList<CartGoodsInfo>?>>

    /**
     * 添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartRequest): Observable<BaseResponse<Int>>

    /**
     * 删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartRequest): Observable<BaseResponse<String>>

    /**
     * 提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartRequest): Observable<BaseResponse<Int>>
}
