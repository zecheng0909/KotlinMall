package com.cheng.baselibrary.rx

import com.cheng.baselibrary.data.protocol.BaseResponse
import rx.Observable
import rx.functions.Func1

/**
 * User: Cheng
 * Date: 2018-01-06
 * Time: 16:28
 * Describe: Func1的封装,将返回结果转为Boolean类型,适用于只关心结果是true 或者 false的对方
 */

open class BaseFuncBoolean<T> : Func1<BaseResponse<T>, Observable<Boolean>> {
    override fun call(t: BaseResponse<T>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}