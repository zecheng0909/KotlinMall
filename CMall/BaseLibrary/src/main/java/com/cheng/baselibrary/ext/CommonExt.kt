package com.cheng.baselibrary.ext

import com.cheng.baselibrary.rx.BaseSubscriber
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-03
 * Time: 17:43
 * Describe:都是一些公共扩展方法
 */

/**
 * 扩展了Observable,简化了代码
 * */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}