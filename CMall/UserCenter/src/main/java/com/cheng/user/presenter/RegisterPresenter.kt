package com.cheng.user.presenter

import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.user.presenter.view.RegisterView

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-02
 * Time: 23:55
 * Describe:
 */

class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(){
        //假设注册成功
        mView.onSucceed(200)
    }
}