package com.cheng.user.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.cheng.baselibrary.ext.enable
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.provider.router.RouterPath
import com.cheng.user.R
import com.cheng.user.data.protocol.UserInfo
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.LoginPresenter
import com.cheng.user.presenter.view.LoginView
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-01-08
 * Time: 22:06
 * Describe: 登录页
 */

@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView,
        View.OnClickListener {

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
        forgetPwdTv.setOnClickListener(this)
        headerBar.getRightView().setOnClickListener(this)
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
            headerBar.getRightView() -> {
                startActivity<RegisterActivity>()
            }

            loginBtn -> {
                mPresenter.login(mobile = mobileEt.text.toString(),
                        pwd = pwdEt.text.toString(), pushId = "")
            }

            forgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    /**
     * 登录成功回调
     */
    override fun onLoginResult(userInfo: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(userInfo)
        finish()
    }

    /**
     * 检查输入框是否有文本
     */
    private fun isBtnEnabled(): Boolean {
        return mobileEt.text.isNullOrEmpty().not() and
                pwdEt.text.isNullOrEmpty().not()
    }
}
