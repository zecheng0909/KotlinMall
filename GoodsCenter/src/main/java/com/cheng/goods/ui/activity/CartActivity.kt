package com.cheng.goods.ui.activity

import android.os.Bundle
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.goods.R
import com.cheng.goods.ui.fragment.CartListFragment

/**
 * User: Cheng
 * Date: 2018-01-31
 * Time: 17:15
 * Describe: 购物车单独展示页面
 */

class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartListFragment).setBackVisible(true)
    }

}