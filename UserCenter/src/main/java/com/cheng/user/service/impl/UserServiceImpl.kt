package com.cheng.user.service.impl

import com.cheng.baselibrary.ext.convert
import com.cheng.baselibrary.ext.convertBoolean
import com.cheng.user.data.protocol.UserInfo
import com.cheng.user.data.repository.UserRepository
import com.cheng.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 15:25
 * Describe: User模块的Service接口的具体实现,数据层
 */

class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    /**
     * 注册
     */
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile = mobile, pwd = pwd, verifyCode = verifyCode)
                .convertBoolean()
    }

    /**
     * 登录
     */
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile = mobile, pwd = pwd, pushId = pushId)
                .convert()
    }

    /**
     * 找回密码
     */
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile = mobile, verifyCode = verifyCode)
                .convertBoolean()
    }

    /**
     * 重置密码
     */
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile = mobile, pwd = pwd)
                .convertBoolean()
    }

    /**
     * 修改用户信息
     */
    override fun editUser(userIcon: String, userName: String, gender: String, sign: String): Observable<UserInfo> {
        return repository.editUser(userIcon = userIcon, userName = userName, gender = gender, sign = sign)
                .convert()
    }

}