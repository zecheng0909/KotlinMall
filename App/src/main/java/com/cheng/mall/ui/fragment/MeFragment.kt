package com.cheng.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.baselibrary.ext.loadUrl
import com.cheng.baselibrary.ui.fragment.BaseFragment
import com.cheng.mall.R
import com.cheng.mall.ui.activity.SettingActivity
import com.cheng.order.ui.activity.OrderActivity
import com.cheng.order.ui.activity.ShipAddressActivity
import com.cheng.provider.common.afterLogin
import com.cheng.provider.common.isLogined
import com.cheng.user.ui.activity.LoginActivity
import com.cheng.user.ui.activity.UserInfoActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * User: Cheng
 * Date: 2018-01-11
 * Time: 14:10
 * Describe: 我的Fragment
 */

class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()

        initData()
    }

    override fun onClick(view: View) {
        when (view) {

            userIconIv, userNameTv -> {
                if (isLogined()) {
                    startActivity<UserInfoActivity>()
                } else {
                    startActivity<LoginActivity>()
                }
            }

            settingTv -> {
                startActivity<SettingActivity>()
            }

            addressTv -> {
                startActivity<ShipAddressActivity>()
            }

            allOrderTv -> {
                goToOrderActivity(OrderStatus.ORDER_ALL)
            }

            waitPayOrderTv -> {
                goToOrderActivity(OrderStatus.ORDER_WAIT_PAY)
            }

            waitConfirmOrderTv -> {
                goToOrderActivity(OrderStatus.ORDER_WAIT_CONFIRM)
            }

            completeOrderTv -> {
                goToOrderActivity(OrderStatus.ORDER_COMPLETED)
            }
        }
    }

    /**
     * 跳转至订单列表页
     */
    private fun goToOrderActivity(orderStatus: Int) {
        afterLogin {
            startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to orderStatus)
        }
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        userIconIv.setOnClickListener(this)
        userNameTv.setOnClickListener(this)
        settingTv.setOnClickListener(this)
        addressTv.setOnClickListener(this)
        allOrderTv.setOnClickListener(this)
        waitPayOrderTv.setOnClickListener(this)
        waitConfirmOrderTv.setOnClickListener(this)
        completeOrderTv.setOnClickListener(this)
    }

    /**
     * 初始化数据,根据登录状态判断
     */
    private fun initData() {
        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                userIconIv.loadUrl(userIcon)
            }
            userNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            userIconIv.setImageResource(R.drawable.icon_default_user)
            userNameTv.text = getString(R.string.un_login_text)
        }
    }
}