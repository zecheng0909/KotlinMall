package com.cheng.goods.service

import com.kotlin.goods.data.protocol.CartGoodsInfo
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-26
 * Time: 09:24
 * Describe:
 */

interface CartService {

    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

    fun getCartList(): Observable<MutableList<CartGoodsInfo>?>

    fun deleteCartList(list: List<Int>): Observable<Boolean>

    fun submitCart(list: List<CartGoodsInfo>, totalPrice: Long): Observable<Int>

}