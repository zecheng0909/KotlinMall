package com.cheng.baselibrary.ui.fragment

import android.os.Bundle
import com.cheng.baselibrary.common.BaseApplication
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.baselibrary.injection.component.DaggerActivityComponent
import com.cheng.baselibrary.injection.module.ActivityModule
import com.cheng.baselibrary.injection.module.LifecycleProviderModule
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.presenter.view.BaseView
import com.cheng.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-08
 * Time: 14:14
 * Describe:
 */

abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    private lateinit var loadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent()

        injectComponent()

        loadingDialog = ProgressLoading.create(activity)
    }

    override fun showLoading() {
        loadingDialog.showLoading()
    }

    override fun hideLoading() {
        loadingDialog.hideLoading()
    }

    override fun onError(errorText: String) {
        activity.toast(errorText)
    }

    /**
     * 初始化业务层的Component
     */
    abstract fun injectComponent()

    /**
     * 初始化Activity级的注解器
     */
    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}