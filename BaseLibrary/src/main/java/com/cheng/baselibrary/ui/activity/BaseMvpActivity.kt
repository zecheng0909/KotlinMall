package com.cheng.baselibrary.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.common.BaseApplication
import com.cheng.baselibrary.injection.component.ActivityComponent
import com.cheng.baselibrary.injection.component.DaggerActivityComponent
import com.cheng.baselibrary.injection.module.ActivityModule
import com.cheng.baselibrary.injection.module.LifecycleProviderModule
import com.cheng.baselibrary.presenter.BasePresenter
import com.cheng.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * User: Cheng
 * Date: 2018-01-02
 * Time: 23:41
 * Describe: 实现了BaseView接口的Activity基类,声明了持有Presenter对象
 */

abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent()

        injectComponent()
    }

    abstract fun injectComponent()

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    /**
     * 初始化Activity级的注解器
     */
    fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

}