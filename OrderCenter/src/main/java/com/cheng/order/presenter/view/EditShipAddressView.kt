package com.cheng.order.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 09:30
 * Describe: 添加/编辑收货地址的回调
 */

interface EditShipAddressView : BaseView {

    fun onAddShipAddressResult(result: Boolean)

    fun onEditShipAddressResult(result: Boolean)
}