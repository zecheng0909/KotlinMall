package com.cheng.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.goods.R
import com.cheng.goods.event.AddCartEvent
import com.cheng.goods.event.GoodsDetailImageEvent
import com.cheng.goods.ui.adapter.GoodsDetailVpAdapter
import com.cheng.provider.common.afterLogin
import com.eightbitlab.rxbus.Bus
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * User: Cheng
 * Date: 2018-01-20
 * Time: 11:26
 * Describe: 商品详情页
 */

class GoodsDetailActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        goodsDetailTab.tabMode = TabLayout.MODE_FIXED
        goodsDetailTab.setupWithViewPager(goodsDetailVp)
        goodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)

        leftIv.setOnClickListener(this)
        addCartBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            leftIv -> {
                finish()
            }

            addCartBtn -> {
                afterLogin {
                    Bus.send(AddCartEvent())
                }
            }

        }
    }

}