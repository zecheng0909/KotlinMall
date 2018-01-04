package com.cheng.user.service.impl

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.baselibrary.rx.BaseException
import com.cheng.user.data.repository.UserRepository
import com.cheng.user.service.UserService
import rx.Observable
import rx.functions.Func1

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-03
 * Time: 15:25
 * Describe:
 */

class UserServiceImpl : UserService {

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return UserRepository().register(mobile, pwd, verifyCode)
                .flatMap(Func1<BaseResponse<String>, Observable<Boolean>> {
                    if (it.status != 0) {
                        return@Func1 Observable.error(BaseException(it.status, it.message))
                    }
                    Observable.just(true)
                })

    }

}