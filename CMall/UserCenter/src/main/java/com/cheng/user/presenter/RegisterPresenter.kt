package com.cheng.user.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.user.presenter.view.RegisterView
import com.cheng.user.service.UserService
import javax.inject.Inject
import javax.inject.Named

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-02
 * Time: 23:55
 * Describe: 处理逻辑调用
 */

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    @field:[Named("service1")]
    lateinit var service: UserService

    @Inject
    @field:[Named("service2")]
    lateinit var service2: UserService

    fun register(mobile: String, verifyCode: String, pwd: String) {


        service.register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(2001)
                    }
                })
    }

    fun register2(mobile: String, verifyCode: String, pwd: String) {


        service2.register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(200100)
                    }
                })
    }
}