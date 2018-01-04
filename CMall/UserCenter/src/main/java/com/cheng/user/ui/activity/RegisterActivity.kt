package com.cheng.user.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.presenter.RegisterPresenter
import com.cheng.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * User: wangzecheng (514118702@qq.com)
 * Date: 2018-01-03
 * Time: 17:37
 * Describe:注册页
 */


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onRegisterResult(code: Int) {
        toast("$code")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        registerBtn.setOnClickListener {
            mPresenter.register(mobileEt.text.toString(), verifyCodeEt.text.toString(), pwdEt.text.toString())
        }
    }
}
