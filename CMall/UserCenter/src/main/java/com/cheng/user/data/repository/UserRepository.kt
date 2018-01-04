package com.cheng.user.data.repository

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.user.data.api.UserApi
import com.cheng.user.data.protocol.RegisterRequest
import rx.Observable

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-04
 * Time: 17:30
 * Describe:
 */

class UserRepository {

    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResponse<String>> {

        return RetrofitFactory.retrofitFactory.create(UserApi::class.java)
                .register(RegisterRequest(mobile, pwd, verifyCode))

    }
}