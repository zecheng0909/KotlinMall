package com.cheng.baselibrary.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.common.BaseApplication
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.baselibrary.injection.component.DaggerActivityComponent
import com.cheng.baselibrary.injection.module.ActivityModule
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-02
 * Time: 23:41
 * Describe:
 */

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }

}