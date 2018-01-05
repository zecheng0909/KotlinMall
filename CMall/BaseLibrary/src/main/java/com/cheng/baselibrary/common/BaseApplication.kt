package com.cheng.baselibrary.common

import android.app.Application
import com.cheng.baselibrary.injection.component.AppComponent
import com.cheng.baselibrary.injection.component.DaggerAppComponent
import com.cheng.baselibrary.injection.module.AppModule

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-05
 * Time: 19:38
 * Describe:
 */

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initInjection()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}