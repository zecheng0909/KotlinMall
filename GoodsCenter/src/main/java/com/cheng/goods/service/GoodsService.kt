package com.cheng.goods.service

import com.kotlin.goods.data.protocol.GoodsInfo
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 17:39
 * Describe:
 */

interface GoodsService {

    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<GoodsInfo>?>

    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<GoodsInfo>?>
}