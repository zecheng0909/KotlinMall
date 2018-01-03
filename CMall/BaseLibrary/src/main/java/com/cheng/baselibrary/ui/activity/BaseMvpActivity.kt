package com.cheng.baselibrary.ui.activity

import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.presenter.view.BaseView

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-02
 * Time: 23:41
 * Describe:
 */

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var mPresenter: T

}