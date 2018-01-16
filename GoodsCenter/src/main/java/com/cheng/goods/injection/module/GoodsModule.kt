package com.cheng.goods.injection.module

import com.cheng.goods.service.GoodsService
import com.cheng.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * User: Cheng
 * Date: 2018-01-16
 * Time: 14:10
 * Describe:
 */

@Module
class GoodsModule {

    @Provides
    fun providesGoodsService(service: GoodsServiceImpl): GoodsService {
        return service
    }

}