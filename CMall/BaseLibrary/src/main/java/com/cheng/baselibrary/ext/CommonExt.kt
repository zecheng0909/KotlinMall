package com.cheng.baselibrary.ext

import com.cheng.baselibrary.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 17:43
 * Describe:都是一些公共扩展方法
 */

/**
 * 扩展了Observable,简化了代码
 * 完成了 指定订阅线程在 io
 * 接收订阅在 main
 * */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}