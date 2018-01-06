package com.cheng.baselibrary.rx

import rx.Subscriber

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 17:37
 * Describe: Subscriber向上抽取的基类,空实现三个方法,简化了代码
 * 使用时重写关心的事件即可
 */

open class BaseSubscriber<T> : Subscriber<T>() {

    override fun onError(e: Throwable?) {

    }

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }
}