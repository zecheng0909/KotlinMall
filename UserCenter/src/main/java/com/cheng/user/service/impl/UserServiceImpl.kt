package com.cheng.user.service.impl

import com.cheng.baselibrary.ext.convertBoolean
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

}