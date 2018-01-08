package com.cheng.user.ui.activity

import android.os.Bundle
import android.view.View
import com.cheng.baselibrary.common.AppManager
import com.cheng.baselibrary.ext.enable
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.LoginPresenter
import com.cheng.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-01-08
 * Time: 22:06
 * Describe: 登录页
 */


class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView,
        View.OnClickListener {

    var time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {

        loginBtn.enable(mobileEt, ::isBtnEnabled)
        loginBtn.enable(pwdEt, ::isBtnEnabled)

        loginBtn.setOnClickListener(this)
        headerBar.getRightText().setOnClickListener(this)

    }

    /**
     * 初始化依赖对象
     */
    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onClick(view: View) {
        when (view) {
            headerBar.getRightText() -> {
                startActivity<RegisterActivity>()
            }

            loginBtn -> {
                mPresenter.login(mobile = mobileEt.text.toString(),
                        pwd = pwdEt.text.toString(), pushId = "")
            }
        }
    }

    /**
     * 登录成功回调
     */
    override fun onLoginResult(message: String) {
        toast(message)
    }

    override fun onBackPressed() {
        doubleBackExit()
    }

    /**
     * 双击back退出app
     */
    private fun doubleBackExit() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - time > 2000) {
            toast("再次点击返回键退出应用")
            time = currentTime
        } else {
            AppManager.appManager.exitApp(this)
        }
    }

    /**
     * 检查输入框是否有文本
     */
    private fun isBtnEnabled(): Boolean {
        return mobileEt.text.isNullOrEmpty().not() and
                pwdEt.text.isNullOrEmpty().not()
    }
}
