package com.cheng.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.order.R
import com.cheng.order.injection.component.DaggerOrderComponent
import com.cheng.order.injection.module.OrderModule
import com.cheng.order.presenter.OrderConfirmPresenter
import com.cheng.order.presenter.view.OrderConfirmView
import com.cheng.provider.router.RouterPath
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.data.protocol.OrderInfo
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 11:13
 * Describe: 订单确认页
 */

@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(),
        OrderConfirmView, View.OnClickListener {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var orderId: Int = 0

    private lateinit var orderGoodsAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        loadData()
    }

    override fun onClick(v: View?) {
        when (v) {
            selectShipTv -> {
                startActivity<ShipAddressActivity>()
            }
        }

    }

    /**
     * 获取订单数据
     */
    private fun loadData() {
        mPresenter.getOrderById(orderId = orderId)
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        orderGoodsRv.layoutManager = LinearLayoutManager(this)
        orderGoodsAdapter = OrderGoodsAdapter(this)
        orderGoodsRv.adapter = orderGoodsAdapter

        selectShipTv.setOnClickListener(this)
    }

    /**
     * 获取订单数据的回调
     */
    override fun onGetOrderByIdResult(orderInfo: OrderInfo) {
        totalPriceTv.text = "合计:${YuanFenConverter.changeF2Y(orderInfo.totalPrice)}"
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