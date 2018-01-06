package com.cheng.user.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 17:38
 * Describe: 业务层的Component,依赖于ActivityComponent
 */

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(UserModule::class)])
interface UserComponent {
    fun inject(activity: RegisterActivity)
}