package com.kotlin.order.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.kotlin.order.data.protocol.AddShipAddressRequest
import com.kotlin.order.data.protocol.DeleteShipAddressRequest
import com.kotlin.order.data.protocol.EditShipAddressRequest
import com.kotlin.order.data.protocol.ShipAddressInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:00
 * Describe: 地址管理模块接口
 */

interface ShipAddressApi {

    /**
     * 添加收货地址
     */
    @POST("shipAddress/add")
    fun addShipAddress(@Body req: AddShipAddressRequest): Observable<BaseResponse<String>>

    /**
     * 删除收货地址
     */
    @POST("shipAddress/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressRequest): Observable<BaseResponse<String>>

    /**
     * 修改收货地址
     */
    @POST("shipAddress/modify")
    fun editShipAddress(@Body req: EditShipAddressRequest): Observable<BaseResponse<String>>

    /**
     * 查询收货地址列表
     */
    @POST("shipAddress/getList")
    fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddressInfo>?>>

}
