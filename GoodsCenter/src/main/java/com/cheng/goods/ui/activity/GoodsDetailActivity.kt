package com.cheng.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import android.view.View
import com.cheng.baselibrary.ui.activity.BaseActivity
import com.cheng.goods.R
import com.cheng.goods.common.GoodsConstant
import com.cheng.goods.event.AddCartEvent
import com.cheng.goods.event.UpdateCartCountEvent
import com.cheng.goods.ui.adapter.GoodsDetailVpAdapter
import com.cheng.provider.common.afterLogin
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * User: Cheng
 * Date: 2018-01-20
 * Time: 11:26
 * Describe: 商品详情页
 */

class GoodsDetailActivity : BaseActivity(), View.OnClickListener {

    private lateinit var badgeView: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initBadgeView()
        initObserve()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        goodsDetailTab.tabMode = TabLayout.MODE_FIXED
        goodsDetailTab.setupWithViewPager(goodsDetailVp)
        goodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)

        leftIv.setOnClickListener(this)
        addCartBtn.setOnClickListener(this)
        enterCartTv.setOnClickListener(this)
    }

    /**
     * 初始化角标控件
     */
    private fun initBadgeView() {
        badgeView = QBadgeView(this)
        badgeView.badgeGravity = Gravity.END or Gravity.TOP
        badgeView.setBadgeTextSize(8F, true)
        badgeView.setGravityOffset(18F, 0F, true)
        badgeView.bindTarget(enterCartTv)
        loadCartCount()
    }

    /**
     *  购物车数量变化事件
     */
    private fun initObserve() {
        Bus.observe<UpdateCartCountEvent>()
                .subscribe {
                    loadCartCount()
                }
                .registerInBus(this)
    }

    /**
     * 设置购物车角标数字
     */
    private fun loadCartCount() {
        badgeView.badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
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

            enterCartTv -> {
                startActivity<CartActivity>()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}