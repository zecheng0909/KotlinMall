package com.cheng.order.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.ShipAddressInfo

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 14:00
 * Describe: 地址管理页的回调
 */

interface ShipAddressView : BaseView {

    fun onGetShipAddressListResult(result: MutableList<ShipAddressInfo>?)

    fun onSetDefaultShipAddressResult(result: Boolean)

    fun onDeleteShipAddressResult(result: Boolean)
}