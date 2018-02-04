package com.cheng.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.cheng.baselibrary.ext.setVisible
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.order.R
import com.cheng.order.event.UpDateAddressEvent
import com.cheng.order.injection.component.DaggerOrderComponent
import com.cheng.order.injection.module.OrderModule
import com.cheng.order.presenter.OrderConfirmPresenter
import com.cheng.order.presenter.view.OrderConfirmView
import com.cheng.provider.router.RouterPath
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.data.protocol.OrderInfo
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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

    private var orderInfo: OrderInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        initObserve()
        loadData()
    }

    /**
     * 更新地址显示状态
     */
    private fun upDateAddressView() {
        val address = orderInfo!!.shipAddress
        if (address != null) {
            shipView.setVisible(true)
            selectShipTv.setVisible(false)
            shipNameTv.text = address.shipUserName
            shipAddressTv.text = address.shipAddress
        } else {
            shipView.setVisible(false)
            selectShipTv.setVisible(true)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            shipView, selectShipTv -> {
                startActivity<ShipAddressActivity>()
            }

            submitOrderBtn -> {
                mPresenter.submitOrder(orderInfo!!)
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
        shipView.setOnClickListener(this)
        submitOrderBtn.setOnClickListener(this)
    }

    /**
     * 初始化接收选择收货地址事件
     */
    private fun initObserve() {
        Bus.observe<UpDateAddressEvent>()
                .subscribe {
                    orderInfo?.shipAddress = it.addressInfo
                    upDateAddressView()
                }
                .registerInBus(this)
    }

    /**
     * 获取订单数据的回调
     */
    override fun onGetOrderByIdResult(result: OrderInfo) {
        totalPriceTv.text = "合计:${YuanFenConverter.changeF2Y(result.totalPrice)}"
        orderGoodsAdapter.setData(result.orderGoodsList)
        orderInfo = result
        upDateAddressView()
    }

    /**
     * 提交订单的回调
     */
    override fun onSubmitOrderResult(result: Boolean) {
        if (result) {
            toast("提交成功")
        } else {
            toast("提交失败")
        }
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

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}