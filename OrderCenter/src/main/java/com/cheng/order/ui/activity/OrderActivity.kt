package com.cheng.order.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.order.R
import com.cheng.order.ui.adapter.OrderVpAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import kotlinx.android.synthetic.main.activity_order.*

/**
 * User: Cheng
 * Date: 2018-02-04
 * Time: 19:01
 * Describe: 订单列表
 */

class OrderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        orderTab.tabMode = TabLayout.MODE_FIXED
        orderTab.setupWithViewPager(orderVp)
        orderVp.adapter = OrderVpAdapter(supportFragmentManager, this)

        orderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
    }
}