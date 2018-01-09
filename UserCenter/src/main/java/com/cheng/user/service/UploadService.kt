package com.cheng.user.service

import rx.Observable

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 17:52
 * Describe: 上传云服务模块的Service接口
 */

interface UploadService {

    fun getUploadToken(): Observable<String>
}