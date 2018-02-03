package com.cheng.order.service

import com.kotlin.order.data.protocol.ShipAddressInfo
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 09:16
 * Describe:
 */

interface ShipAddressService {

    /**
     * 添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String)
            : Observable<Boolean>

    /**
     * 获取收货地址列表
     */
    fun getShipAddressList(): Observable<MutableList<ShipAddressInfo>?>

    /**
     * 修改收货地址
     */
    fun editShipAddress(address: ShipAddressInfo): Observable<Boolean>

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<Boolean>
}