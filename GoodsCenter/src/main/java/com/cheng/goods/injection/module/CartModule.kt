package com.cheng.goods.injection.module

import com.cheng.goods.service.CartService
import com.cheng.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * User: Cheng
 * Date: 2018-01-26
 * Time: 9:50
 * Describe:
 */

@Module
class CartModule {

    @Provides
    fun providesCartService(service: CartServiceImpl): CartService {
        return service
    }

}