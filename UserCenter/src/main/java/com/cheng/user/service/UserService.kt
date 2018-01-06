package com.cheng.user.service

import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 15:24
 * Describe: User模块的Service接口
 */

interface UserService {

    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>

}