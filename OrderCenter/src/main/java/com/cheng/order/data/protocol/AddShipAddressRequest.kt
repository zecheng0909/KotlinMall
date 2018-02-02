package com.kotlin.order.data.protocol

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:00
 * Describe: 添加收货地址请求
 */

data class AddShipAddressRequest(val shipUserName: String, val shipUserMobile: String, val shipAddress: String)
