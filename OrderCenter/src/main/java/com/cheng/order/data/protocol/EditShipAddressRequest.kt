package com.kotlin.order.data.protocol

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:16
 * Describe: 修改收货地址
 */

data class EditShipAddressRequest(val id:Int,
                              val shipUserName:String,
                              val shipUserMobile:String,
                              val shipAddress:String,
                              val shipIsDefault:Int)
