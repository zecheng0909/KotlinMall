package com.cheng.user.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 13:08
 * Describe: 重置密码的View回调接口
 */

interface ResetPwdView :BaseView {

    fun onResetPwdResult(message:String)
}