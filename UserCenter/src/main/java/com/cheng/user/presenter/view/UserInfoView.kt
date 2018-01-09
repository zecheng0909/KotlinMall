package com.cheng.user.presenter.view

import com.cheng.baselibrary.presenter.view.BaseView
import com.cheng.user.data.protocol.UserInfo

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 14:50
 * Describe: 个人信息的View回调接口
 */

interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result: String)

    fun onEditUserTokenResult(userInfo: UserInfo)
}