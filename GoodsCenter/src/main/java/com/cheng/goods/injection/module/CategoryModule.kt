package com.cheng.goods.injection.module

import com.cheng.goods.service.CategoryService
import com.cheng.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * User: Cheng
 * Date: 2018-01-12
 * Time: 10:14
 * Describe:
 */

@Module
class CategoryModule {

    @Provides
    fun providesCategoryService(service: CategoryServiceImpl): CategoryService {
        return service
    }
}