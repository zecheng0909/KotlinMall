package com.cheng.user.injection.module

import com.cheng.user.service.UploadService
import com.cheng.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 17:51
 * Describe:
 */

@Module
class UploadModule {

    @Provides
    fun providesUserService(service: UploadServiceImpl): UploadService {
        return service
    }
}