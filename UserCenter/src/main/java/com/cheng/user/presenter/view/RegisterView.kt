package com.cheng.user.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView

/**
 * User: Cheng
 * Date: 2018-01-02
 * Time: 23:56
 * Describe: 注册页面的View接口
 */

interface RegisterView :BaseView {

    fun onRegisterResult(message:String)
}