package com.cheng.order.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.cheng.baselibrary.ui.fragment.BaseMvpFragment
import com.cheng.order.R
import com.cheng.order.injection.component.DaggerOrderComponent
import com.cheng.order.injection.module.OrderModule
import com.cheng.order.presenter.OrderListPresenter
import com.cheng.order.presenter.view.OrderListView
import com.cheng.order.ui.activity.OrderDetailActivity
import com.cheng.order.ui.adapter.OrderAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.OrderInfo
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * User: Cheng
 * Date: 2018-02-04
 * Time: 19:06
 * Describe:
 */

class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_order, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        orderRv.layoutManager = LinearLayoutManager(activity)
        orderAdapter = OrderAdapter(activity)
        orderRv.adapter = orderAdapter

        orderAdapter.setOnOptClickListener(object : OrderAdapter.OnOptClickListener {
            override fun onOptClick(optType: Int, orderInfo: OrderInfo) {
                when (optType) {
                    OrderConstant.OPT_ORDER_CONFIRM -> {
                        showOrderConfirmDialog(orderInfo.id)
                    }

                    OrderConstant.OPT_ORDER_PAY -> {
                        toast("去支付")
                    }

                    OrderConstant.OPT_ORDER_CANCEL -> {
                        showOrderCancelDialog(orderInfo.id)
                    }
                }
            }

        })

        orderAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<OrderInfo> {
            override fun onItemClick(item: OrderInfo, position: Int) {
                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }
        })
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        multiStateView.viewState = MultiStateView.VIEW_STATE_LOADING
        mPresenter.getOrderList(arguments.getInt(OrderConstant.KEY_ORDER_STATUS, -1))
    }

    /**
     * 获取订单列表的回调
     */
    override fun onGetOrderListResult(result: MutableList<OrderInfo>?) {
        if (result != null && result.size != 0) {
            multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            orderAdapter.setData(result)
        } else {
            multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     * 完成订单的回调
     */
    override fun onConfirmOrderResult(result: Boolean) {
        toast("订单已完成")
        loadData()
    }

    /**
     * 取消订单的回调
     */
    override fun onCancelOrderResult(result: Boolean) {
        toast("订单已取消")
        loadData()
    }

    /**
     * 取消订单询问弹窗
     */
    fun showOrderCancelDialog(orderId: Int) {
        AlertView("取消订单", "是否取消该订单", "取消", null, arrayOf("确定"), activity, AlertView.Style.Alert,
                OnItemClickListener { _, position ->
                    if (position == 0) {
                        mPresenter.cancelOrder(orderId = orderId)
                    }
                }).show()
    }

    /**
     * 确认收货询问弹窗
     */
    fun showOrderConfirmDialog(orderId: Int) {
        AlertView("确认收货", "是否确认收货", "取消", null, arrayOf("确定"), activity, AlertView.Style.Alert,
                OnItemClickListener { _, position ->
                    if (position == 0) {
                        mPresenter.confirmOrder(orderId = orderId)
                    }
                }).show()
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