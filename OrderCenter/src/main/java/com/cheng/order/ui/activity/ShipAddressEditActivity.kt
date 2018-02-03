package com.cheng.order.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.order.R
import com.cheng.order.injection.component.DaggerShipAddressComponent
import com.cheng.order.injection.module.ShipAddressModule
import com.cheng.order.presenter.EditShipAddressPresenter
import com.cheng.order.presenter.view.EditShipAddressView
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddressInfo
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 09:05
 * Describe: 收货地址编辑页
 */

class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {

    private var shipAddressInfo: ShipAddressInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)
        initView()
        loadData()
    }

    /**
     * 从 intent 获取数据
     * 如果不为null,说明需要编辑收货地址
     * 如果为null,说明需要新增收货地址
     */
    private fun loadData() {
        shipAddressInfo = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        shipAddressInfo?.let {
            shipNameEt.setText(it.shipUserName)
            shipMobileEt.setText(it.shipUserMobile)
            shipAddressEt.setText(it.shipAddress)
        }
    }

    private fun initView() {
        saveBtn.onClick {
            if (shipNameEt.text.isNullOrEmpty()) {
                toast("请填写收货人")
                return@onClick
            }
            if (shipMobileEt.text.isNullOrEmpty()) {
                toast("请填写联系电话")
                return@onClick
            }
            if (shipAddressEt.text.isNullOrEmpty()) {
                toast("请填写详细地址")
                return@onClick
            }
            if (shipAddressInfo == null) {
                addShipAddress()
            } else {
                editShipAddress()
            }
        }
    }

    /**
     * 添加收货地址
     */
    private fun addShipAddress() {
        mPresenter.addShipAddress(shipUserName = shipNameEt.text.toString(),
                shipUserMobile = shipMobileEt.text.toString(), shipAddress = shipAddressEt.text.toString())
    }

    /**
     * 编辑收货地址
     */
    private fun editShipAddress() {
        shipAddressInfo!!.shipUserName = shipNameEt.text.toString()
        shipAddressInfo!!.shipUserMobile = shipMobileEt.text.toString()
        shipAddressInfo!!.shipAddress = shipAddressEt.text.toString()
        mPresenter.editShipAddress(shipAddressInfo!!)
    }

    /**
     * 添加收货地址的回调
     */
    override fun onAddShipAddressResult(result: Boolean) {
        if (result) {
            toast("添加成功")
            finish()
        } else {
            toast("添加失败")
        }
    }

    /**
     * 编辑收货地址的回调
     */
    override fun onEditShipAddressResult(result: Boolean) {
        if (result) {
            toast("保存成功")
            finish()
        } else {
            toast("添加失败o")
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