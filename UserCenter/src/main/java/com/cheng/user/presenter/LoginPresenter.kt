package com.cheng.user.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.user.data.protocol.UserInfo
import com.cheng.user.presenter.view.LoginView
import com.cheng.user.service.UserService
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-08
 * Time: 22:19
 * Describe: 登录页面,处理逻辑调用
 */

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var service: UserService

    fun login(mobile: String, pwd: String, pushId: String) {

        if (!checkNerWork()) return

        mView.showLoading()
        service.login(mobile = mobile, pwd = pwd, pushId = pushId)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(userInfo: UserInfo) {
                        mView.onLoginResult("成功 $userInfo")
                    }
                }, lifecycleProvider)
    }

}