package com.cheng.user.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.ext.enable
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.ResetPwdPresenter
import com.cheng.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 12:49
 * Describe: 重置密码页面
 */


class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {

        confirmBtn.enable(pwdEt, ::isBtnEnabled)
        confirmBtn.enable(pwdConfirmEt, ::isBtnEnabled)

        confirmBtn.onClick {
            if (pwdEt.text.toString() != pwdConfirmEt.text.toString()){
                toast("两次输入密码不一致")
                return@onClick
            }
                mPresenter.resetPwd(intent.getStringExtra("mobile"), pwdEt.text.toString())
        }
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

    /**
     * 重置成功的回调
     */
    override fun onResetPwdResult(message: String) {
        toast("密码重置成功")
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

    /**
     * 检查输入框是否有文本
     */
    private fun isBtnEnabled(): Boolean {
        return pwdEt.text.isNullOrEmpty().not() and
                pwdConfirmEt.text.isNullOrEmpty().not()
    }
}
