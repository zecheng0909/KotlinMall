package com.cheng.user.injection.component

import com.cheng.baselibrary.injection.PerComponentScope
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.user.injection.module.UploadModule
import com.cheng.user.injection.module.UserModule
import com.cheng.user.ui.activity.*
import dagger.Component

/**
 * User: Cheng
 * Date: 2018-01-05
 * Time: 17:38
 * Describe: 业务层的Component,依赖于ActivityComponent
 */

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(UserModule::class), (UploadModule::class)])
interface UserComponent {

    fun inject(registerActivity: RegisterActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(forgetPwdActivity: ForgetPwdActivity)

    fun inject(resetPwdActivity: ResetPwdActivity)

    fun inject(userInfoActivity: UserInfoActivity)

}