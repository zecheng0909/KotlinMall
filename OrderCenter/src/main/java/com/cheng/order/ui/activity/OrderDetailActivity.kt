package com.cheng.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.order.R
import com.cheng.order.injection.component.DaggerOrderComponent
import com.cheng.order.injection.module.OrderModule
import com.cheng.order.presenter.OrderDetailPresenter
import com.cheng.order.presenter.view.OrderDetailView
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.data.protocol.OrderInfo
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_order_detail.*

/**
 * User: Cheng
 * Date: 2018-02-05
 * Time: 12:42
 * Describe: 订单详情页
 */

class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {

    private lateinit var orderGoodsAdapter: OrderGoodsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        initView()
        loadData()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        orderGoodsRv.layoutManager = LinearLayoutManager(this)
        orderGoodsAdapter = OrderGoodsAdapter(this)
        orderGoodsRv.adapter = orderGoodsAdapter
    }

    private fun loadData() {
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, 0))
    }

    /**
     * 获取订单详情回调
     */
    override fun onGetOrderDetailResult(orderInfo: OrderInfo) {
        shipNameTv.setContentText(orderInfo.shipAddress!!.shipUserName)
        shipMobileTv.setContentText(orderInfo.shipAddress!!.shipUserMobile)
        shipAddressTv.setContentText(orderInfo.shipAddress!!.shipAddress)
        totalPriceTv.setContentText("$${YuanFenConverter.changeF2Y(orderInfo.totalPrice)}")
        orderGoodsAdapter.setData(orderInfo.orderGoodsList)
    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerOrderComponent.builder()
                .activityComponent(activityComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }
}