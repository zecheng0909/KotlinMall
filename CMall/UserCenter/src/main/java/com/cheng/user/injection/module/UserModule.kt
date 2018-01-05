package com.cheng.user.injection.module

import com.cheng.user.service.UserService
import com.cheng.user.service.impl.UserServiceImpl
import com.cheng.user.service.impl.UserServiceImpl2
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-05
 * Time: 17:25
 * Describe:
 */

@Module
class UserModule {

    @Provides
    @Named("service1")
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }


    @Provides
    @Named("service2")
    fun providesUserService2(service: UserServiceImpl2): UserService {
        return service
    }
}