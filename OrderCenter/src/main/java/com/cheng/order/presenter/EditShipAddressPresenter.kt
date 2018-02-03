package com.cheng.order.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.order.presenter.view.EditShipAddressView
import com.cheng.order.service.ShipAddressService
import com.kotlin.order.data.protocol.ShipAddressInfo
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 09:25
 * Describe:
 */

class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /**
     * 添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName = shipUserName, shipUserMobile = shipUserMobile,
                shipAddress = shipAddress).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                super.onNext(t)
                mView.onAddShipAddressResult(t)
            }
        }, lifecycleProvider)
    }

    /**
     * 编辑收货地址
     */
    fun editShipAddress(shipAddressInfo: ShipAddressInfo) {
        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(shipAddressInfo)
                .execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                super.onNext(t)
                mView.onEditShipAddressResult(t)
            }
        }, lifecycleProvider)
    }
}