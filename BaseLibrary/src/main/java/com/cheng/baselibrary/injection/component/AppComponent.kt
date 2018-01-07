package com.cheng.baselibrary.injection.component

import android.content.Context
import com.cheng.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 19:36
 * Describe:全局的Component
 */

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun context(): Context

}