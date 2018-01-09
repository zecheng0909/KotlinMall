package com.cheng.user.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.user.presenter.view.ForgetPwdView
import com.cheng.user.presenter.view.ResetPwdView
import com.cheng.user.service.UserService
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 13:08
 * Describe: 重置密码页面,处理逻辑调用
 */

class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var service: UserService

    /**
     * 重复输入密码后重置密码
     */
    fun resetPwd(mobile: String, pwd: String) {

        if (!checkNerWork()) {
            return
        }
        mView.showLoading()
        service.resetPwd(mobile = mobile, pwd = pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onResetPwdResult("重置成功")
                    }
                }, lifecycleProvider)
    }

}