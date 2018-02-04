package com.cheng.order.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ui.fragment.BaseMvpFragment
import com.cheng.order.R
import com.cheng.order.injection.component.DaggerOrderComponent
import com.cheng.order.injection.module.OrderModule
import com.cheng.order.presenter.OrderListPresenter
import com.cheng.order.presenter.view.OrderListView
import com.cheng.order.ui.adapter.OrderAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.data.protocol.OrderInfo
import kotlinx.android.synthetic.main.fragment_order.*
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

        orderAdapter.setOnOptClickListener(object : OrderAdapter.OnOptClickListener{
            override fun onOptClick(optType: Int, orderInfo: OrderInfo) {
                when(optType){
                    OrderConstant.OPT_ORDER_CONFIRM ->{
                        toast("确认收货")
                    }

                    OrderConstant.OPT_ORDER_PAY ->{
                        toast("去支付")
                    }

                    OrderConstant.OPT_ORDER_CANCEL ->{
                        toast("取消")
                    }
                }
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