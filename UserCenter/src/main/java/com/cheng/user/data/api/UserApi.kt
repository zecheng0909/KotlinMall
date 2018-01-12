package com.cheng.user.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.user.data.protocol.*
import com.kotlin.user.data.protocol.EditUserRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-04
 * Time: 16:00
 * Describe: User模块,访问网络需要的api
 */

interface UserApi {

    @POST("userCenter/register")
    fun register(@Body registerRequest: RegisterRequest): Observable<BaseResponse<String>>

    @POST("userCenter/login")
    fun login(@Body loginRequest: LoginRequest): Observable<BaseResponse<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body forgetPwdRequest: ForgetPwdRequest): Observable<BaseResponse<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body resetPwdRequest: ResetPwdRequest): Observable<BaseResponse<String>>

    @POST("userCenter/editUser")
    fun editUser(@Body editUserRequest: EditUserRequest): Observable<BaseResponse<UserInfo>>
}