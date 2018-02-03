package com.cheng.order.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.order.presenter.view.ShipAddressView
import com.cheng.order.service.ShipAddressService
import com.kotlin.order.data.protocol.ShipAddressInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 13:57
 * Describe:
 */

class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /**
     * 获取收货地址列表
     */
    fun getShipAddressList() {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList()
                .execute(object : BaseSubscriber<MutableList<ShipAddressInfo>?>(mView) {
                    override fun onNext(t: MutableList<ShipAddressInfo>?) {
                        super.onNext(t)
                        mView.onGetShipAddressListResult(t)
                    }
                }, lifecycleProvider)
    }

    /**
     * 设置默认收货地址
     */
    fun setDefaultShipAddress(address: ShipAddressInfo) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address = address)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.onSetDefaultShipAddressResult(t)
                    }
                }, lifecycleProvider)
    }

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id: Int) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.deleteShipAddress(id = id)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.onDeleteShipAddressResult(t)
                    }
                }, lifecycleProvider)
    }
}