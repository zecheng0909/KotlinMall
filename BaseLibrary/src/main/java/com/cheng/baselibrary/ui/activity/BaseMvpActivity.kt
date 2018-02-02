package com.cheng.baselibrary.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
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
 * Date: 2018-01-02
 * Time: 23:41
 * Describe: 实现了BaseView接口的Activity基类,声明了持有Presenter对象
 */

abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    private lateinit var loadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityComponent()

        injectComponent()

        loadingDialog = ProgressLoading.create(this)

        ARouter.getInstance().inject(this)
    }

    override fun showLoading() {
        loadingDialog.showLoading()
    }

    override fun hideLoading() {
        loadingDialog.hideLoading()
    }

    override fun onError(errorText: String) {
        toast(errorText)
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
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

}