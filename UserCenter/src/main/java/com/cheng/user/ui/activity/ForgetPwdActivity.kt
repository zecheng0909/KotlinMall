package com.cheng.user.ui.activity

import android.os.Bundle
import android.view.View
import com.cheng.baselibrary.ext.enable
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.ForgetPwdPresenter
import com.cheng.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 11:42
 * Describe: 忘记密码页面
 */


class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView,
        View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {

        nextBtn.enable(mobileEt, ::isBtnEnabled)
        nextBtn.enable(verifyCodeEt, ::isBtnEnabled)

        verifyCodeBtn.setOnClickListener(this)
        nextBtn.setOnClickListener(this)
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

            nextBtn -> {
                mPresenter.forgetPwd(mobile = mobileEt.text.toString(),
                        verifyCode = verifyCodeEt.text.toString())
            }
        }
    }

    /**
     * 验证成功的回调
     */
    override fun onForgetPwdResult(message: String) {
        startActivity<ResetPwdActivity>("mobile" to mobileEt.text.toString())
    }

    /**
     * 检查输入框是否有文本
     */
    private fun isBtnEnabled(): Boolean {
        return mobileEt.text.isNullOrEmpty().not() and
                verifyCodeEt.text.isNullOrEmpty().not()
    }
}
