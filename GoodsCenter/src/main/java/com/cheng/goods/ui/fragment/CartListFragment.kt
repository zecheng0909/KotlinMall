package com.cheng.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ui.fragment.BaseMvpFragment
import com.cheng.goods.R
import com.cheng.goods.injection.component.DaggerCartComponent
import com.cheng.goods.injection.module.CartModule
import com.cheng.goods.presenter.CartListPresenter
import com.cheng.goods.presenter.view.CartListView
import com.kennyc.view.MultiStateView
import com.kotlin.goods.data.protocol.CartGoodsInfo
import com.kotlin.goods.ui.adapter.CartGoodsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * User: Cheng
 * Date: 2018-01-27
 * Time: 9:33
 * Describe: 购物车列表
 */

class CartListFragment : BaseMvpFragment<CartListPresenter>(), CartListView {

    private lateinit var cartGoodsAdapter: CartGoodsAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_cart, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCartGoodsRv()
        loadData()
    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerCartComponent.builder()
                .activityComponent(activityComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 初始化了购物车列表
     */
    private fun initCartGoodsRv() {
        cartGoodsRv.layoutManager = LinearLayoutManager(activity)
        cartGoodsAdapter = CartGoodsAdapter(activity)
        cartGoodsRv.adapter = cartGoodsAdapter
    }

    /**
     * 获得购物车列表的回调
     */
    override fun onGetCartListResult(result: MutableList<CartGoodsInfo>?) {
        if (result != null && result.size != 0) {
            cartGoodsAdapter.setData(result)
            multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     * 获取分类数据
     */
    private fun loadData() {
        mPresenter.getCartList()
    }

}