package com.cheng.user.service

import com.cheng.user.data.protocol.UserInfo
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 15:24
 * Describe: User模块的Service接口
 */

interface UserService {

    /**
     * 注册
     * @mobile 手机号
     * @pwd 密码
     * @verifyCode 验证码
     */
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>

    /**
     * 登录
     * @mobile 手机号
     * @pwd 密码
     * @pushId 推送ID
     */
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>

    /**
     * 找回密码
     * @mobile 手机号
     * @verifyCode 验证码
     */
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>

    /**
     * 重置密码
     * @mobile 手机号
     * @pwd 密码
     */
    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>

}