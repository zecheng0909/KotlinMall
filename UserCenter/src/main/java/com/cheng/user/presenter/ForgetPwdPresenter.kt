package com.cheng.user.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.user.presenter.view.ForgetPwdView
import com.cheng.user.service.UserService
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 11:51
 * Describe: 忘记密码页面,处理逻辑调用
 */

class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var service: UserService

    /**
     * 校验手机号和验证码
     */
    fun forgetPwd(mobile: String, verifyCode: String) {

        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        service.forgetPwd(mobile = mobile, verifyCode = verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onForgetPwdResult("验证成功")
                    }
                }, lifecycleProvider)
    }

}