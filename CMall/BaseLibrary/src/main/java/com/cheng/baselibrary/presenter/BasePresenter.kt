package com.cheng.baselibrary.presenter

import com.cheng.baselibrary.presenter.view.BaseView

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-02
 * Time: 23:39
 * Describe:
 */

open class BasePresenter<T : BaseView> {

    lateinit var mView: T
}