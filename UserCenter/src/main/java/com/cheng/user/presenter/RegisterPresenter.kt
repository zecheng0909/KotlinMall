package com.cheng.user.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.user.presenter.view.RegisterView
import com.cheng.user.service.UserService
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-02
 * Time: 23:55
 * Describe: 注册页面,处理逻辑调用
 */

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var service: UserService

    fun register(mobile: String, verifyCode: String, pwd: String) {

        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        service.register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult("200")
                    }
                }, lifecycleProvider)
    }

}