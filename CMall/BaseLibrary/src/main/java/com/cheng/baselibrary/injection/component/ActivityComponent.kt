package com.cheng.baselibrary.injection.component

import android.app.Activity
import com.cheng.baselibrary.injection.ActivityScope
import com.cheng.baselibrary.injection.module.ActivityModule
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 19:43
 * Describe: Activity级别的Component
 */

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun activity(): Activity
}