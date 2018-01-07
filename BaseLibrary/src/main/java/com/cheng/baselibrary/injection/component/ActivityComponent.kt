package com.cheng.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.cheng.baselibrary.injection.ActivityScope
import com.cheng.baselibrary.injection.module.ActivityModule
import com.cheng.baselibrary.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 19:43
 * Describe: Activity级别的Component
 */

@ActivityScope
@Component(dependencies = [(AppComponent::class)],
        modules = [(ActivityModule::class), (LifecycleProviderModule::class)])
interface ActivityComponent {

    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}