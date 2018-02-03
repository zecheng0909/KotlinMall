package com.cheng.order.service.impl

import com.cheng.baselibrary.ext.convert
import com.cheng.baselibrary.ext.convertBoolean
import com.cheng.order.service.ShipAddressService
import com.kotlin.order.data.protocol.ShipAddressInfo
import com.kotlin.order.data.repository.ShipAddressRepository
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-03
 * Time: 09:18
 * Describe:
 */

class ShipAddressServiceImpl @Inject constructor() : ShipAddressService {

    @Inject
    lateinit var shipAddressRepository: ShipAddressRepository

    /**
     * 添加收货地址
     */
    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String)
            : Observable<Boolean> {
        return shipAddressRepository.addShipAddress(shipUserName = shipUserName, shipUserMobile = shipUserMobile,
                shipAddress = shipAddress).convertBoolean()
    }

    /**
     * 获取收货地址列表
     */
    override fun getShipAddressList(): Observable<MutableList<ShipAddressInfo>?> {
        return shipAddressRepository.getShipAddressList().convert()
    }

    /**
     * 修改收货地址信息
     */
    override fun editShipAddress(address: ShipAddressInfo): Observable<Boolean> {
        return shipAddressRepository.editShipAddress(address = address).convertBoolean()
    }

    /**
     * 删除收货地址
     */
    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return shipAddressRepository.deleteShipAddress(id = id).convertBoolean()
    }
}