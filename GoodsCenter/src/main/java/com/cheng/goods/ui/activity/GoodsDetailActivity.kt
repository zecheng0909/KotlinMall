package com.cheng.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.toast

/**
 * User: Cheng
 * Date: 2018-01-20
 * Time: 11:26
 * Describe: 商品详情页
 */

class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        toast("${intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1)}")
    }

    private fun initView() {
        goodsDetailTab.tabMode = TabLayout.MODE_FIXED
        goodsDetailTab.setupWithViewPager(goodsDetailVp)
        goodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
    }

}