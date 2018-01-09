package com.cheng.user.data.api

import com.cheng.baselibrary.data.protocol.BaseResponse
import retrofit2.http.POST
import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 18:00
 * Describe:
 */

interface UploadApi {

    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResponse<String>>
}