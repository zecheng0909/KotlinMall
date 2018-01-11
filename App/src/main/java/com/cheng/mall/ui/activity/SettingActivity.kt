package com.cheng.mall.ui.activity

import android.os.Bundle
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.cheng.baselibrary.ext.onClick
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.mall.R
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 16:20
 * Describe: 设置页面
 */

class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        logoutBtn.onClick {
            AlertView("是否退出登录", null, "取消", null,
                    arrayOf("退出"),
                    this, AlertView.Style.ActionSheet, object : OnItemClickListener {
                override fun onItemClick(o: Any?, position: Int) {
                    UserPrefsUtils.putUserInfo(null)
                    finish()
                }
            }).show()
        }
    }
}