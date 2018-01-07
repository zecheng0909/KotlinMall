package com.cheng.baselibrary.presenter

import android.content.Context
import com.cheng.baselibrary.presenter.view.BaseView
import com.kotlin.base.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-02
 * Time: 23:39
 * Describe: Presenter的基类,声明了持有view对象
 */

open class BasePresenter<T : BaseView> {

    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNerWork(): Boolean {
        return NetWorkUtils.isNetWorkAvailable(context)
    }

}