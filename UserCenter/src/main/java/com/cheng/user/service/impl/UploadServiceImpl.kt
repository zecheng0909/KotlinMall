package com.cheng.user.service.impl

import com.cheng.baselibrary.ext.convert
import com.cheng.user.data.repository.UploadRepository
import com.cheng.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 17:54
 * Describe:
 */

class UploadServiceImpl @Inject constructor() : UploadService {

    @Inject
    lateinit var uploadRepository: UploadRepository

    /**
     * 获得上传凭证
     */
    override fun getUploadToken(): Observable<String> {
        return uploadRepository.getUploadToken().convert()
    }
}