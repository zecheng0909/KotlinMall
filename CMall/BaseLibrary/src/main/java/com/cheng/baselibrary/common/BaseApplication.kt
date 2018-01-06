package com.cheng.baselibrary.common

import android.app.Application
import com.cheng.baselibrary.injection.component.AppComponent
import com.cheng.baselibrary.injection.component.DaggerAppComponent
import com.cheng.baselibrary.injection.module.AppModule

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 19:38
 * Describe: 自定义Application
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