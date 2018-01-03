package com.cheng.user.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.presenter.RegisterPresenter
import com.cheng.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onSucceed(code: Int) {
        toast("$code")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        registerBtn.setOnClickListener {
            mPresenter.register("","","")
        }
    }
}
