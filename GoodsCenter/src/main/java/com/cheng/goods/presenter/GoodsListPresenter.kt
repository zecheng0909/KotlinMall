package com.cheng.goods.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.goods.presenter.view.GoodsListView
import com.cheng.goods.service.GoodsService
import com.kotlin.goods.data.protocol.GoodsInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-15
 * Time: 17:33
 * Describe:
 */

class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId = categoryId, pageNo = pageNo)
                .execute(object : BaseSubscriber<MutableList<GoodsInfo>?>(mView) {
                    override fun onNext(t: MutableList<GoodsInfo>?) {
                        mView.getGoodsListResult(t)
                    }
                }, lifecycleProvider)

    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword = keyword, pageNo = pageNo)
                .execute(object : BaseSubscriber<MutableList<GoodsInfo>?>(mView) {
                    override fun onNext(t: MutableList<GoodsInfo>?) {
                        mView.getGoodsListResult(t)
                    }
                }, lifecycleProvider)

    }
}