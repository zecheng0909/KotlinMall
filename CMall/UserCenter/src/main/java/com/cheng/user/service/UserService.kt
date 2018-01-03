package com.cheng.user.service

import rx.Observable

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-03
 * Time: 15:24
 * Describe:
 */

interface UserService {

    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>

}