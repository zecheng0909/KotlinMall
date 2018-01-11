package com.cheng.user.ui.activity

import android.os.Bundle
import android.view.View
import com.cheng.baselibrary.ext.enable
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.RegisterPresenter
import com.cheng.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 17:37
 * Describe: 注册页
 */


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView,
        View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {

        registerBtn.enable(mobileEt, ::isBtnEnabled)
        registerBtn.enable(verifyCodeEt, ::isBtnEnabled)
        registerBtn.enable(pwdEt, ::isBtnEnabled)
        registerBtn.enable(pwdConfirmEt, ::isBtnEnabled)

        verifyCodeBtn.setOnClickListener(this)
        registerBtn.setOnClickListener(this)
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
            verifyCodeBtn -> {
                verifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }

            registerBtn -> {
                if (pwdEt.text.toString() != pwdConfirmEt.text.toString()) {
                    toast("两次输入密码不正确")
                    return
                }
                mPresenter.register(mobileEt.text.toString(),
                        pwdEt.text.toString(), verifyCodeEt.text.toString())
            }
        }
    }

    /**
     * 注册成功的回调
     */
    override fun onRegisterResult(message: String) {
        toast(message)
        finish()
    }

    /**
     * 检查输入框是否有文本
     */
    private fun isBtnEnabled(): Boolean {
        return mobileEt.text.isNullOrEmpty().not() and
                verifyCodeEt.text.isNullOrEmpty().not() and
                pwdEt.text.isNullOrEmpty().not() and
                pwdConfirmEt.text.isNullOrEmpty().not()
    }
}
