package com.kotlin.order.data.repository

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.kotlin.order.data.api.ShipAddressApi
import com.kotlin.order.data.protocol.AddShipAddressRequest
import com.kotlin.order.data.protocol.DeleteShipAddressRequest
import com.kotlin.order.data.protocol.EditShipAddressRequest
import com.kotlin.order.data.protocol.ShipAddressInfo
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-02-01
 * Time: 15:20
 * Describe: 收货地址数据层
 */

class ShipAddressRepository @Inject constructor() {

    /**
     * 添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String)
            : Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory
                .create(ShipAddressApi::class.java)
                .addShipAddress(AddShipAddressRequest(shipUserName, shipUserMobile, shipAddress))
    }

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory
                .create(ShipAddressApi::class.java)
                .deleteShipAddress(DeleteShipAddressRequest(id))
    }

    /**
     * 修改收货地址信息
     */
    fun editShipAddress(address: ShipAddressInfo): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory
                .create(ShipAddressApi::class.java)
                .editShipAddress(EditShipAddressRequest(address.id, address.shipUserName,
                        address.shipUserMobile, address.shipAddress, address.shipIsDefault))
    }

    /**
     * 获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddressInfo>?>> {
        return RetrofitFactory.retrofitFactory
                .create(ShipAddressApi::class.java)
                .getShipAddressList()
    }
}
