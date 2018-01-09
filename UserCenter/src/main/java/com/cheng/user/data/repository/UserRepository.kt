package com.cheng.user.data.repository

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.user.data.api.UserApi
import com.cheng.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-04
 * Time: 17:30
 * Describe: User模块网络请求层
 */

class UserRepository @Inject constructor() {


    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory.create(UserApi::class.java)
                .register(RegisterRequest(mobile = mobile, pwd = pwd, verifyCode = verifyCode))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.retrofitFactory.create(UserApi::class.java)
                .login(LoginRequest(mobile = mobile, pwd = pwd, pushId = pushId))
    }

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory.create(UserApi::class.java)
                .forgetPwd(ForgetPwdRequest(mobile = mobile, verifyCode = verifyCode))
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory.create(UserApi::class.java)
                .resetPwd(ResetPwdRequest(mobile = mobile, pwd = pwd))
    }
}