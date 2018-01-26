package com.cheng.goods.service.impl

import com.cheng.baselibrary.ext.convert
import com.cheng.goods.service.CartService
import com.kotlin.goods.data.repository.CartRepository
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-26
 * Time: 09:26
 * Describe:
 */

class CartServiceImpl @Inject constructor() : CartService {

    @Inject
    lateinit var cartRepository: CartRepository

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String,
                         goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return cartRepository.addCart(goodsId = goodsId, goodsDesc = goodsDesc, goodsIcon = goodsIcon,
                goodsPrice = goodsPrice, goodsCount = goodsCount, goodsSku = goodsSku).convert()
    }

}