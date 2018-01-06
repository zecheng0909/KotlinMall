package com.cheng.user.service.impl

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.ext.convertBoolean
import com.cheng.user.data.api.UserApi
import com.cheng.user.data.protocol.RegisterRequest
import com.cheng.user.data.repository.UserRepository
import com.cheng.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 15:25
 * Describe: User模块的Service接口的具体实现,用来处理数据
 */

class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return repository.register(mobile, pwd, verifyCode)
                .convertBoolean()

    }

    fun register2(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return RetrofitFactory.retrofitFactory.create(UserApi::class.java)
                .register(RegisterRequest(mobile, pwd, verifyCode))
                .convertBoolean()

    }

}