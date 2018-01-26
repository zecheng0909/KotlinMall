package com.kotlin.goods.data.repository

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.kotlin.goods.data.api.CartApi
import com.kotlin.goods.data.protocol.AddCartRequest
import com.kotlin.goods.data.protocol.CartGoodsInfo
import com.kotlin.goods.data.protocol.DeleteCartRequest
import com.kotlin.goods.data.protocol.SubmitCartRequest
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-25
 * Time: 15:22
 * Describe: 购物车数据层
 */

class CartRepository @Inject constructor() {

    /**
     * 获取购物车列表
     */
    fun getCartList(): Observable<BaseResponse<MutableList<CartGoodsInfo>?>> {
        return RetrofitFactory.retrofitFactory.create(CartApi::class.java).getCartList()
    }

    /**
     * 添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<BaseResponse<Int>> {
        return RetrofitFactory.retrofitFactory.create(CartApi::class.java)
                .addCart(AddCartRequest(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
    }

    /**
     * 删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory.create(CartApi::class.java).deleteCartList(DeleteCartRequest(list))
    }

    /**
     * 购物车结算
     */
    fun submitCart(list: MutableList<CartGoodsInfo>, totalPrice: Long): Observable<BaseResponse<Int>> {
        return RetrofitFactory.retrofitFactory.create(CartApi::class.java).submitCart(SubmitCartRequest(list, totalPrice))
    }


}
