package com.cheng.order.event

import com.kotlin.order.data.protocol.ShipAddressInfo

/**
 * User: Cheng
 * Date: 2018-02-04
 * Time: 20:55
 * Describe: 选择收货地址事件
 */

class UpDateAddressEvent(val addressInfo: ShipAddressInfo) {
}