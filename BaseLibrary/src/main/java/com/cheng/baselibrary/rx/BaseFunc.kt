package com.cheng.baselibrary.rx

import com.cheng.baselibrary.common.ResultCode
import com.cheng.baselibrary.data.protocol.BaseResponse
import rx.Observable
import rx.functions.Func1

/**
 * User: Cheng
 * Date: 2018-01-06
 * Time: 16:39
 * Describe: Func1的封装,将返回结果转为T类型,也就是具体返回的类型
 */

class BaseFunc<T> : Func1<BaseResponse<T>, Observable<T>> {
    override fun call(t: BaseResponse<T>): Observable<T> {
        if (t.status != ResultCode.SUCCEED) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}