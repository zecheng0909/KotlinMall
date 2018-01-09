package com.cheng.user.data.repository

import com.cheng.baselibrary.data.net.RetrofitFactory
import com.cheng.baselibrary.data.protocol.BaseResponse
import com.cheng.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 17:57
 * Describe: 上传云服务模块网络请求层
 */

class UploadRepository @Inject constructor() {

    fun getUploadToken(): Observable<BaseResponse<String>> {
        return RetrofitFactory.retrofitFactory.create(UploadApi::class.java)
                .getUploadToken()
    }
}