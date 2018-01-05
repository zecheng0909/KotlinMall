package com.cheng.user.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-05
 * Time: 17:38
 * Describe:
 */

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(UserModule::class)])
interface UserComponent {
    fun inject(activity: RegisterActivity)
}