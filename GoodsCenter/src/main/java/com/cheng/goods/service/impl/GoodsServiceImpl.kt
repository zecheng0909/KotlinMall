package com.cheng.goods.service.impl

import com.cheng.baselibrary.ext.convert
import com.cheng.goods.service.GoodsService
import com.kotlin.goods.data.protocol.GoodsInfo
import com.kotlin.goods.data.repository.GoodsRepository
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 17:40
 * Describe:
 */

class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var goodsRepository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<GoodsInfo>?> {
        return goodsRepository.getGoodsList(categoryId = categoryId, pageNo = pageNo)
                .convert()
    }
}