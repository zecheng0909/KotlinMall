package com.cheng.user.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView

/**
 * User: Cheng
 * Date: 2018-01-08
 * Time: 22:18
 * Describe: 登录页面的View接口
 */

interface LoginView :BaseView {

    fun onLoginResult(message:String)
}