package com.cheng.baselibrary.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-05
 * Time: 19:43
 * Describe: LifecycleProvider 构建的 Module
 */

@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return lifecycleProvider
    }

}