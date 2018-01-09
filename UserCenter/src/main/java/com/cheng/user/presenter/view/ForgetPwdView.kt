package com.cheng.user.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 11:49
 * Describe: 忘记密码的View回调接口
 */

interface ForgetPwdView :BaseView {

    fun onForgetPwdResult(message:String)
}