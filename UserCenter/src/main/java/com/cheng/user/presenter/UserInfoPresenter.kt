package com.cheng.user.presenter

import com.cheng.baselibrary.ext.execute
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.rx.BaseSubscriber
import com.cheng.user.data.protocol.UserInfo
import com.cheng.user.presenter.view.UserInfoView
import com.cheng.user.service.UploadService
import com.cheng.user.service.UserService
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 14:58
 * Describe: 个人信息页面,处理逻辑调用
 */

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var uploadService: UploadService

    @Inject
    lateinit var userService: UserService

    fun getUploadToken() {
        mView.showLoading()
        uploadService.getUploadToken()
                .execute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onGetUploadTokenResult(t)
                    }
                }, lifecycleProvider)
    }

    fun editUser(userIcon: String, userName: String, gender: String, sign: String) {
        mView.showLoading()
        userService.editUser(userIcon = userIcon, userName = userName, gender = gender, sign = sign)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onEditUserTokenResult(t)
                    }
                }, lifecycleProvider)
    }

}