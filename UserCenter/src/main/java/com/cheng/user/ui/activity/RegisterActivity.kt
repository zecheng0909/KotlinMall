package com.cheng.user.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.common.AppManager
import com.cheng.baselibrary.ext.onClick
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
 * Describe:注册页
 */


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    var time: Long = 0

    override fun onRegisterResult(message: String) {
        toast(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn.onClick {
            mPresenter.register(mobileEt.text.toString(), verifyCodeEt.text.toString(), pwdEt.text.toString())
        }

    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
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
}
