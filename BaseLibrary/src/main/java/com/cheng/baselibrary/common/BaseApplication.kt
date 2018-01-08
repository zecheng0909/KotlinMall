package com.cheng.baselibrary.common

import android.app.Application
import android.content.Context
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


    companion object {

        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()

        initInjection()

        context = this

    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}