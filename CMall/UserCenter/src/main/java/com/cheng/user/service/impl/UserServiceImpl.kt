package com.cheng.user.service.impl

import com.cheng.user.service.UserService
import rx.Observable

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-03
 * Time: 15:25
 * Describe:
 */

class UserServiceImpl : UserService {

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return Observable.just(true)
    }

}