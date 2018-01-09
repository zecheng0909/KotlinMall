package com.cheng.user.presenter

import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.user.presenter.view.UserInfoView
import com.cheng.user.service.UserService
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 14:58
 * Describe: 用户资料页面,处理逻辑调用
 */

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var service: UserService

}