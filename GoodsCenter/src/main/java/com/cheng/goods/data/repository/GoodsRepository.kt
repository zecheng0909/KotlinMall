package com.kotlin.goods.data.repository

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.goods.data.api.GoodsApi
import com.kotlin.goods.data.protocol.GetGoodsDetailRequest
import com.kotlin.goods.data.protocol.GetGoodsListByKeywordRequest
import com.kotlin.goods.data.protocol.GetGoodsListRequest
import com.kotlin.goods.data.protocol.GoodsInfo
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 17:04
 * Describe: 商品模块网络请求层
 */

class GoodsRepository @Inject constructor() {

    /**
     * 根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResponse<MutableList<GoodsInfo>?>> {
        return RetrofitFactory.retrofitFactory.create(GoodsApi::class.java)
                .getGoodsList(GetGoodsListRequest(categoryId, pageNo))
    }

    /**
     * 根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResponse<MutableList<GoodsInfo>?>> {
        return RetrofitFactory.retrofitFactory.create(GoodsApi::class.java)
                .getGoodsListByKeyword(GetGoodsListByKeywordRequest(keyword, pageNo))
    }

    /**
     * 商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResponse<GoodsInfo>> {
        return RetrofitFactory.retrofitFactory.create(GoodsApi::class.java)
                .getGoodsDetail(GetGoodsDetailRequest(goodsId))
    }
}
