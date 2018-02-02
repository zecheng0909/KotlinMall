package com.kotlin.order.data.protocol

import android.os.Parcel
import android.os.Parcelable

/**
 * User: Cheng
 * Date: 2018-02-1
 * Time: 15:00
 * Describe: 收货地址数据类
 */

//@Parcelize
data class ShipAddressInfo(
        val id: Int,
        var shipUserName: String,
        var shipUserMobile: String,
        var shipAddress: String,
        var shipIsDefault: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(shipUserName)
        parcel.writeString(shipUserMobile)
        parcel.writeString(shipAddress)
        parcel.writeInt(shipIsDefault)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShipAddressInfo> {
        override fun createFromParcel(parcel: Parcel): ShipAddressInfo {
            return ShipAddressInfo(parcel)
        }

        override fun newArray(size: Int): Array<ShipAddressInfo?> {
            return arrayOfNulls(size)
        }
    }
}
