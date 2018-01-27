package com.cheng.goods.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.goods.data.protocol.CategoryInfo
import com.cheng.goods.presenter.view.CartListView
import com.cheng.goods.presenter.view.CategoryView
import com.cheng.goods.service.CartService
import com.kotlin.goods.data.protocol.CartGoodsInfo
import com.kotlin.goods.data.protocol.GoodsInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-27
 * Time: 10:21
 * Describe: 购物车列表逻辑层
 */

class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartService

    /**
     * 获取购物车列表
     */
    fun getCartList() {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        cartService.getCartList()
                .execute(object : BaseSubscriber<MutableList<CartGoodsInfo>?>(mView) {
                    override fun onNext(result: MutableList<CartGoodsInfo>?) {
                        mView.onGetCartListResult(result)
                    }
                }, lifecycleProvider)
    }

    /**
     * 删除购物车商品
     */
    fun deleteCartList(list: List<Int>) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        cartService.deleteCartList(list)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(result: Boolean) {
                        mView.onDeleteCartListResult(result)
                    }
                }, lifecycleProvider)
    }


}