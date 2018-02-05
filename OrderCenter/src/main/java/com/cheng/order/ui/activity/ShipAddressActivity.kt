package com.cheng.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.order.R
import com.cheng.order.event.UpDateAddressEvent
import com.cheng.order.injection.component.DaggerShipAddressComponent
import com.cheng.order.injection.module.ShipAddressModule
import com.cheng.order.presenter.ShipAddressPresenter
import com.cheng.order.presenter.view.ShipAddressView
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddressInfo
import com.kotlin.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 09:02
 * Describe: 地址管理页
 */

class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    private lateinit var shipAddressAdapter: ShipAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /**
     * 加载收货信息列表
     */
    private fun loadData() {
        mPresenter.getShipAddressList()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        addressRv.layoutManager = LinearLayoutManager(this)
        shipAddressAdapter = ShipAddressAdapter(this)
        addressRv.adapter = shipAddressAdapter

        addAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()
        }

        shipAddressAdapter.setOptClickListener(object : ShipAddressAdapter.OnOptClickListener {
            override fun onEdit(item: ShipAddressInfo) {
                startActivity<ShipAddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to item)
            }

            override fun onDelete(id: Int) {
                deleteShipAddress(id)
            }

            override fun onSetDefault(item: ShipAddressInfo) {
                mPresenter.setDefaultShipAddress(address = item)
            }
        })

        shipAddressAdapter.setOnItemClickListener(object :
                BaseRecyclerViewAdapter.OnItemClickListener<ShipAddressInfo> {
            override fun onItemClick(item: ShipAddressInfo, position: Int) {
                Bus.send(UpDateAddressEvent(item))
                finish()
            }
        })
    }

    /**
     * 弹出对话框,选择是否删除收货地址
     */
    private fun deleteShipAddress(id: Int) {
        AlertView("是否删除该收货地址", null, "取消", null, arrayOf("删除"), this, AlertView.Style.Alert,
                OnItemClickListener { _, position ->
                    if (position == 0) {
                        mPresenter.deleteShipAddress(id = id)
                    }
                }).show()
    }

    /**
     * 收货信息列表数据的回调
     */
    override fun onGetShipAddressListResult(result: MutableList<ShipAddressInfo>?) {
        if (result != null && result.size != 0) {
            shipAddressAdapter.setData(result)
            multiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            multiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     * 设置默认收货地址的回调
     */
    override fun onSetDefaultShipAddressResult(result: Boolean) {
        loadData()
        if (!result) {
            toast("设置失败")
        }
    }

    /**
     * 删除收货地址的回调
     */
    override fun onDeleteShipAddressResult(result: Boolean) {
        loadData()
        if (!result) {
            toast("删除失败")
        }
    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerShipAddressComponent.builder()
                .activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }
}