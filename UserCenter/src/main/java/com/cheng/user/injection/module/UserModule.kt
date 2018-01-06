package com.cheng.user.injection.module

import com.cheng.user.service.UserService
import com.cheng.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 17:25
 * Describe:
 */

@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }

}