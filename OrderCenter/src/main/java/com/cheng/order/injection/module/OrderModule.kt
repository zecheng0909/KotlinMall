package com.cheng.order.injection.module

import com.cheng.order.service.OrderService
import com.cheng.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * User: Cheng
 * Date: 2018-02-02
 * Time: 13:01
 * Describe:
 */

@Module
class OrderModule {

    @Provides
    fun providesOrderService(service: OrderServiceImpl): OrderService {
        return service
    }

}