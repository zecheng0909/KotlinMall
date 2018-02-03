package com.cheng.order.injection.module

import com.cheng.order.service.ShipAddressService
import com.cheng.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 13:01
 * Describe:
 */

@Module
class ShipAddressModule {

    @Provides
    fun providesOrderService(service: ShipAddressServiceImpl): ShipAddressService {
        return service
    }

}