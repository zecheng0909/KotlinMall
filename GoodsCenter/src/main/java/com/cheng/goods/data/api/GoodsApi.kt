package com.cheng.goods.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.kotlin.goods.data.protocol.GetGoodsDetailRequest
import com.kotlin.goods.data.protocol.GetGoodsListByKeywordRequest
import com.kotlin.goods.data.protocol.GetGoodsListRequest
import com.kotlin.goods.data.protocol.GoodsInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 22:42
 * Describe: Goods模块,访问网络需要的api
 */

interface GoodsApi {

    /**
     * 获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListRequest): Observable<BaseResponse<MutableList<GoodsInfo>?>>

    /**
     * 获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordRequest):
            Observable<BaseResponse<MutableList<GoodsInfo>?>>

    /**
     * 获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailRequest):
            Observable<BaseResponse<GoodsInfo>>
}