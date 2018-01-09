package com.cheng.user.ui.activity

import android.os.Bundle
import com.bigkoo.alertview.AlertView
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseMvpActivity
import com.cheng.user.R
import com.cheng.user.injection.component.DaggerUserComponent
import com.cheng.user.injection.module.UserModule
import com.cheng.user.presenter.UserInfoPresenter
import com.cheng.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import android.widget.Toast
import com.bigkoo.alertview.OnItemClickListener
import org.jetbrains.anko.toast


/**
 * User: Cheng
 * Date: 2018-01-09
 * Time: 14:51
 * Describe: 用户资料页面
 */

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        userIconView.onClick {
            showAlertView()
        }
    }

    private fun showAlertView() {
//        AlertView()
        AlertView("选择图片", null, "取消", null,
                arrayOf("拍照", "相册"),
                this, AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                when(position){
                    0 -> toast("拍照")
                    1 -> toast("相册")
                }
            }
        }).show()
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
}