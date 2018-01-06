package com.cheng.user.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.user.data.protocol.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-04
 * Time: 16:00
 * Describe: Retrofit需要的Api
 */

interface UserApi {

    @POST("userCenter/register")
    fun register(@Body registerRequest: RegisterRequest): Observable<BaseResponse<String>>
}