package com.cheng.goods.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.goods.presenter.view.GoodsDetailView
import com.cheng.goods.presenter.view.GoodsListView
import com.cheng.goods.service.CartService
import com.cheng.goods.service.GoodsService
import com.kotlin.goods.data.protocol.GoodsInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-22
 * Time: 13:15
 * Describe:
 */

class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService
    @Inject
    lateinit var cartService: CartService

    fun getGoodsDetail(goodsId: Int) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId = goodsId)
                .execute(object : BaseSubscriber<GoodsInfo>(mView) {
                    override fun onNext(t: GoodsInfo) {
                        mView.getGoodsDetailResult(t)
                    }
                }, lifecycleProvider)

    }

    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String,
                goodsPrice: Long, goodsCount: Int, goodsSku: String) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId = goodsId, goodsDesc = goodsDesc, goodsIcon = goodsIcon,
                goodsPrice = goodsPrice, goodsCount = goodsCount, goodsSku = goodsSku)
                .execute(object : BaseSubscriber<Int>(mView) {
                    override fun onNext(t: Int) {
                        mView.addCartResult(t)
                    }
                }, lifecycleProvider)

    }
}