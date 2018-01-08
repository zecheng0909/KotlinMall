package com.cheng.baselibrary.injection.module

import android.content.Context
import com.cheng.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-05
 * Time: 19:36
 * Describe: 全局的Module
 */

@Module
// @Singleton
class AppModule(private val context: BaseApplication) {

    @Provides
    fun providesContext(): Context {
        return context
    }
}