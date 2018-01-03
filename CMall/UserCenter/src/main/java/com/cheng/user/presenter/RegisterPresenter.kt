package com.cheng.user.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.user.presenter.view.RegisterView
import com.cheng.user.service.impl.UserServiceImpl

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-02
 * Time: 23:55
 * Describe:
 */

class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(mobile: String, verifyCode: String, pwd: String) {
        val service = UserServiceImpl()

        service.register(mobile, verifyCode, pwd)
                .execute(object :BaseSubscriber<Boolean>(){
                    override fun onNext(t: Boolean) {
                        mView.onSucceed(2001)
                    }
                })
    }
}